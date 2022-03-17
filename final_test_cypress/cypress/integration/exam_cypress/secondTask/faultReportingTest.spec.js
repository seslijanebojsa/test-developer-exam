describe('Marko reports a breakdown in the building', ()=>{
    
    it('successful fault assignment', ()=>{

        //Login as admistrator (admin@gmail.com, Bar5slova)
        //using 'login' command
        cy.login('admin@gmail.com', 'Bar5slova');

        //registration of a new institution
        cy.contains('Institucije').click();

        cy.addInstitucion('institucija1@gmail.com', 'Bar5slova', 'Institucija 1', 'Novi Sad', 'Laze Nančića', '36', '0216915970');

        //confirmation of message ' Uspesno ste registrovali instituciju! '
        cy.get('.toast-message').invoke('text').then((text) => {
            expect(text).equal(' Uspesno ste registrovali instituciju! ')
        })
        
        //logout
        cy.contains('Izlogujte se').click();


        //Login as Marko
        cy.login('marko@gmail.com', 'Bar5slova');

        //verify marko's login
        //Verify that marko's email is visible
        cy.contains('marko@gmail.com').should('be.visible');

        //Open the malfunction that Marko created
        cy.get('#zgradaStanuje > tbody > tr > :nth-child(4) > a').click();
        
        //click on Mark's failures
        cy.contains('Kvarovi').click();

        cy.get('.pogledaj_1 > .operacije').click();

        cy.contains('izmeni odgovorno lice').click();

        //confirm there are six responsible persons in the list
        cy.get('.table-secondary').should('have.length', 6);

        cy.get('.col-form-label > .form-control').clear().type('Institucija 1');

        //confirm there are only one responsible person in the list after search
        cy.get('.table-secondary').should('have.length', 1);

        cy.contains('Prihvati').click();

        //logout
        cy.contains('Izlogujte se').click();

        //login as 'Institucija 1'
        cy.login('institucija1@gmail.com', 'Bar5slova');

        //Verify that 'institucija1@gmail.com' email is visible
        cy.contains('institucija1@gmail.com').should('be.visible');

        //click on 'Dodeljeni kvarovi'
        cy.contains('Dodeljeni kvarovi').click();

        cy.get('#kvar_1').should('contain', 'Marko Markovic')
            .and('contain', 'Ne radi svetlo u hodniku.');
        
        //Leave a comment within the defect with the text "Rejected"
        cy.get('.operacije').click();

        cy.contains('Kvarovi').click();

        cy.get('#kvar_1').should('contain', 'Marko Markovic')
            .and('contain', 'Ne radi svetlo u hodniku.').find('a').click();

        //enter commentar 'Odbijeno'
        cy.get('#tekstKomentara').clear().type('Odbijeno');

        cy.get('#button_komentar').click();

        //confirm that the comment was successfully added
        cy.get('.toast-message').invoke('text').then((text) => {
            expect(text).equal(' Komentar uspesno dodat ');
        })

        //forward the fault
        cy.get('.prosledi > .operacije').click();

        cy.get('.col-form-label > .form-control').clear().type('Gospodin');

        //confirm there are only one responsible person in the list after search
        cy.get('.table-secondary').should('have.length', 1);

        cy.contains('Prihvati').click();
        
        //catches the previous message, 
        //so we wait for the previous message to be removed
        cy.wait(4000);

        //confirm message  'Odgovorno lice uspesno izmenjeno' 
        cy.get('.toast-message').invoke('text').then((text) => {
            expect(text).equal(' Odgovorno lice uspesno izmenjeno ');
        })

        cy.contains('Dodeljeni kvarovi').click();

        cy.get('#kvar_1').should('not.exist');
        cy.contains('Marko Markovic').should('not.exist');
        cy.contains('Ne radi svetlo u hodniku.').should('not.exist');

        //logout
        cy.contains('Izlogujte se').click();
    })
})