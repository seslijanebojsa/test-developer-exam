describe('testing the company search form', () => {
    beforeEach(()=>{

        //Login as admistrator (admin@gmail.com, Bar5slova)
        cy.login('admin@gmail.com', 'Bar5slova');

        //click on link 'Firme'
        cy.contains('Firme').click();

        //add five new companys
        cy.addCompany('firma1@gmail.com', 'Bar5slova', 'Firma 1', 'Novi Sad', 'Laze Nančića', '36', '1122334455');
        cy.addCompany('firma2@gmail.com', 'Bar5slova', 'Firma 2', 'Novi Sad', 'Laze Nančića', '36', '1122334455');
        cy.addCompany('firma3@gmail.com', 'Bar5slova', 'Firma 3', 'Novi Sad', 'Laze Nančića', '36', '1122334455');
        cy.addCompany('firma4@gmail.com', 'Bar5slova', 'Firma 4', 'Novi Sad', 'Laze Nančića', '36', '1122334455');
        cy.addCompany('firma5@gmail.com', 'Bar5slova', 'Firma 5', 'Novi Sad', 'Laze Nančića', '36', '1122334455');

        //click on link 'Pregled'
        cy.contains('Pregled').click();

        //verify five companys
        cy.get('tbody').find('tr').should('have.length', 5);
        
    })
    describe('successful company search', () => {
        it('enter the email address', () => {

            //verify current url
            cy.url().should('be.equal', 'http://localhost:8080/firme/pregled');
            
            //insert good email in field for search
            cy.get('#filter').clear().type('firma3@gmail.com');
            
            cy.intercept('GET', '/api/admin/firme?page=0&size=5&filter=firma3@gmail.com').as('cekanje')
            cy.contains('Pretraga').click();

            cy.wait('@cekanje');
            cy.get('tbody').find('tr').each(($el, index, $list) =>{
                expect($list).to.have.length(1);
                cy.wrap($el).should('contain.text', 'firma3@gmail.com');                
            })            

            cy.get('tbody').find('tr').should('have.length', 1);

        })
        it('enter the name of company', () => {

            //verify current url
            cy.url().should('be.equal', 'http://localhost:8080/firme/pregled');
            
            //insert the name of company 'Firma 4' in field for search
            cy.get('#filter').clear().type('Firma 4');
            
            //cy.intercept('GET', '/api/admin/firme?page=0&size=5&filter=firma3@gmail.com').as('cekanje')
            cy.contains('Pretraga').click();

            //cy.wait('@cekanje');
            cy.get('tbody').find('tr').each(($el, index, $list) =>{
                expect($list).to.have.length(1);
                cy.wrap($el).should('contain.text', 'Firma 4');                
            })            

            cy.get('tbody').find('tr').should('have.length', 1);

        })
    })
    describe('unsuccessful company search', () => {
        it('enter the invalid email address, without @', () => {

            cy.url().should('be.equal', 'http://localhost:8080/firme/pregled');
            
            cy.get('#filter').clear().type('firma1gmail.com');            
            
            cy.contains('Pretraga').click();

            cy.get('h2').contains('Nijedna firma sa trazenim kriterijumom nije prondajena!')
            
        })
        it('enter a non-existent company name', () => {

            cy.url().should('be.equal', 'http://localhost:8080/firme/pregled');
            
            cy.get('#filter').clear().type('firmaM');            
            
            cy.contains('Pretraga').click();

            cy.get('h2').contains('Nijedna firma sa trazenim kriterijumom nije prondajena!')

        })
    })
})
