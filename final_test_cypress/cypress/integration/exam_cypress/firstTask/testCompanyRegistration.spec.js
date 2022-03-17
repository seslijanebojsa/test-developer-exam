describe('New company registration test ', ()=>{
    beforeEach(()=>{
        //Login as admistrator (admin@gmail.com, Bar5slova)
        //using 'login' command
        cy.login('admin@gmail.com', 'Bar5slova');
        

        //click on link 'Firme'
        cy.contains('Firme').click();
    })
    describe('successful company registration', ()=>{
        it('we fill in all required fields', ()=>{
            //Verify that url has changed
            cy.url().should('be.equal', 'http://localhost:8080/firme');

            cy.addCompany('firma1@gmail.com', 'Bar5slova', 'Firma 1', 'Novi Sad', 'Laze Nančića', '36', '1122334455');

            //Verify message ' Uspesno ste registrovali firmu! '
            cy.get('.toast-message').invoke('text').then((text) => {
                expect(text).equal(' Uspesno ste registrovali firmu! ')
            })

            //Click on 'Pregled' an assertion that URL has changed
            cy.contains('Pregled').click();

            cy.url().should('be.equal', 'http://localhost:8080/firme/pregled');

            //Confirm that the company on adress 'Laze Nančića 36 Novi Sad' has been added
            cy.get('tbody').find('tr').last().should('contain', 'firma1@gmail.com')

            cy.contains('Izlogujte se').click();

        })
    })

    describe('unsucesufle company registration', ()=>{
        it('using already used email address', ()=>{
            //Verify that url has changed
            cy.url().should('be.equal', 'http://localhost:8080/firme');

            cy.addCompany('firma1@gmail.com', 'Bar5slova', 'Firma 1', 'Novi Sad', 'Laze Nančića', '36', '1122334455');

            //Verify message ' E-mail adresa: firma1@gmail.com je zauzeta! '
            cy.get('.toast-message').invoke('text').then((text) => {
                expect(text).equal(' E-mail adresa: firma1@gmail.com je zauzeta! ')
            })

            cy.contains('Izlogujte se').click();

        })
        it('using the wrong email address form', ()=>{
            //Verify that url has changed
            cy.url().should('be.equal', 'http://localhost:8080/firme');
            
            cy.get('#email').clear().type('firma1gmail.com');
            cy.get('#lozinka').clear().type('Bar5slova');
            cy.get('#naziv').clear().type('Firma 1');
            cy.get('#mesto').clear().type('Novi Sad');
            cy.get('#ulica').clear().type('Laze Nančića');
            cy.get('#broj').clear().type('36');
            cy.get('#brojTelefona').clear().type('1122334455');

            //Verify message 'Neispravna email adresa!'
            cy.get('.invalid-feedback').should('have.text', 'Neispravna email adresa!');

            //confirm that the 'Register' button is disabled.
            cy.contains('Registrujte').should('be.disabled');

            cy.contains('Izlogujte se').click();
        })
        it('we will not fill in the email field', ()=>{
            //Verify that url has changed
            cy.url().should('be.equal', 'http://localhost:8080/firme');

            cy.get('#email').clear();
            cy.get('#lozinka').clear().type('Bar5slova');
            cy.get('#naziv').clear().type('Firma 1');
            cy.get('#mesto').clear().type('Novi Sad');
            cy.get('#ulica').clear().type('Laze Nančića');
            cy.get('#broj').clear().type('36');
            cy.get('#brojTelefona').clear().type('1122334455');
            
            //Verify message 'Ovo polje ne sme biti prazno!'
            cy.get('.invalid-feedback').should('have.text', 'Ovo polje ne sme biti prazno!');

            //confirm that the 'Register' button is disabled.
            cy.contains('Registrujte').should('be.disabled');

            cy.contains('Izlogujte se').click();
        })
        it('we will not fill in any fields', ()=>{
            //Verify that url has changed
            cy.url().should('be.equal', 'http://localhost:8080/firme');

            cy.get('#email').clear();
            cy.get('#lozinka').clear();
            cy.get('#naziv').clear();
            cy.get('#mesto').clear();
            cy.get('#ulica').clear();
            cy.get('#broj').clear();
            cy.get('#brojTelefona').clear();

            cy.get(':nth-child(7) > .control-label > b').click();

            cy.get('.invalid-feedback').should('have.length', 7);

            cy.get('.invalid-feedback').each(($el, index, $list) =>{
                expect($el.text()).equal('Ovo polje ne sme biti prazno!')
            })

            //confirm that the 'Register' button is disabled.
            cy.contains('Registrujte').should('be.disabled');

            cy.contains('Izlogujte se').click();

            cy.get('#kvar_1').should('contain', 'Marko Markovic', 'Ne radi svetlo u hodniku.')

        })
    })
})