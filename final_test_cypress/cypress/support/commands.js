// ***********************************************
// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
// Cypress.Commands.add("login", (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add("drag", { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add("dismiss", { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite("visit", (originalFn, url, options) => { ... })

// Example of Custom Command
Cypress.Commands.add('signIn', (email, password) => {
    cy.visit('https://admin-demo.nopcommerce.com/login')

    cy.get('input[name=Email]')
        .clear()
        .type(email)

    cy.get('input[name=Password]')
        .clear()
        .type(password)
        
    cy.get('.button-1')
        .click()
});

Cypress.Commands.add('login', (email, password) => {
    cy.visit('/')

    cy.get('#email')
        .clear()
        .type(email)

    cy.get('#lozinka')
        .clear()
        .type(password)
        
    cy.contains('Ulogujte se')
        .click()
});

Cypress.Commands.add('addCompany', (email, password, companyName, place, street, adressNumber, telefonNumber) => {
    cy.get('#email').clear().type(email);
    cy.get('#lozinka').clear().type(password);
    cy.get('#naziv').clear().type(companyName);
    cy.get('#mesto').clear().type(place);
    cy.get('#ulica').clear().type(street);
    cy.get('#broj').clear().type(adressNumber);
    cy.get('#brojTelefona').clear().type(telefonNumber);
    cy.contains('Registrujte').click();


})

Cypress.Commands.add('addInstitucion', (email, password, companyName, place, street, adressNumber, telefonNumber) => {
    cy.get('#email').clear().type(email);
    cy.get('#lozinka').clear().type(password);
    cy.get('#naziv').clear().type(companyName);
    cy.get('#mesto').clear().type(place);
    cy.get('#ulica').clear().type(street);
    cy.get('#broj').clear().type(adressNumber);
    cy.get('#brojTelefona').clear().type(telefonNumber);
    cy.contains('Registrujte').click();


})

Cypress.Commands.add('resetPassword', (oldPassword, newPassword) => {
    cy.get('input[name=staraLozinka]')
        .clear()
        .type(oldPassword)

    cy.get('input[name=novaLozinka]')
        .clear()
        .type(newPassword)
        

    cy.get('input[name=potvrdaNoveLozinke]')
        .clear()
        .type(newPassword)

    cy.contains('Promenite lozinku').click()	
});

Cypress.Commands.add('loginRequest', (email, password) => {
    cy.request('POST', '/api/ulogujSe', {lozinka:password, email:email})       // Navodimo metodu, url i objekat koji se šalje
             .then((response) => {                                             // Ne moramo da navodimo potpuni url: http://localhost:8080/api/ulogujSe, jer smo naveli baseUrl u cypress.json fajlu
                expect(response.status).to.eq(200)                             // Proveravamo da li je status code == 200
                expect(response.body).to.have.property('token')                // Proveravamo da li postoji property token
                window.localStorage.setItem('jwt', response.body.token)        // Postavljamo token u localStorage. U localStorage-u key će biti 'jwt', a value će biti baš taj token. Da bismo proverili da li je token dobro postavljen: Desni klik > Inspect > Application > Storage > Local Storage
        })

        // Zahtev mozemo da napišemo i na dolenaveden način, gde tačno navodimo šta je koji parametar (možete izabrati bilo koji pristup)
        // cy.request({
        //     method: 'POST',
        //     url: '/api/ulogujSe',
        //     body: {
        //         lozinka: password,
        //         email: email
        //     }
        //   }).then((response) => {                                                                
        //     expect(response.status).to.eq(200)                                              
        //     expect(response.body).to.have.property('token')                             
        //     window.localStorage.setItem('jwt', response.body.token) 
        // });
});

Cypress.Commands.add('deleteTenant', (flatNumber, tenantId) => {
    const token = window.localStorage.getItem('jwt');                           // Čitamo token koji smo sačuvali

    cy.request({                                                                
            method: 'PUT',                                                      // Navodimo metodu i URL
            url:  '/api/admin/stan/' + flatNumber + '/ukloni/' + tenantId,      // URL je u ovom slučaju npr: /api/admin/stan/1/ukloni/2 (ne navodimo potpun url, jer koristimo baseUrl)
            headers: {                                                          // Postavljamo token u okviru headers, da bi request mogao da se izvrši. Ako token nije postavljen dobija se error 401 Unauthorized
                'X-Auth-Token': token,
            }
        }).then((response) => {     
            expect(response.status).to.eq(200)                                  // Proveravamo da li je status code == 200
            expect(response.body.id).to.eq(tenantId)                            // Za ovaj konkretni zahtev, za brisanje stanara iz stana, kao odgovor (response) se vraća objekat, koji predstavlja tog jednog stanara koji je obrisan
        })                                                                      // Znamo da smo obrisali stanara koji ima prosleđeni tenantId, pa očekujemo da i objekat koji je stigao sa servera sadrži upravo tog stanara, tj. da je u okviru objekta polje id == tenantId
});

Cypress.Commands.add('newBuilding', (citi, street, number, numberOfApartments) => {
    
    cy.get('#mesto').clear()
        .type(citi);

    cy.get('#ulica').clear()
        .type(street);

    cy.get('#broj').clear()
        .type(number);

    cy.get('#brojStanova')
        .clear().type(numberOfApartments);
});

Cypress.Commands.add('novaZgrada', (citi, street, number, numberOfApartments) => {
    const token = window.localStorage.getItem('jwt');

    cy.request({
        method: 'POST',
        url: '/api/admin/zgrada/dodaj',
        headers: {
            'X-Auth-Token': token,
        },
        body: {
            broj: 'number',
            brojStanova: 'numberOfApartments',
            lokacija: 'citi',
            ulica: 'street'
        }
           
        }).then((response) =>{
            expect(response.status).to.eq(200),
            expect(response.body).to.have.property(broj, number)
            expect(response.body).to.have.property(brojStanova, numberOfApartments)
            expect(response.body).to.have.property(lokacija, citi)
            expect(response.body).to.have.property(ulica, street)
        })
    
})

// Cypress.Commands.add('novaZgrada', (citi, street, number, numberOfApartments) => {
//     const token = window.localStorage.getItem('jwt');

//     cy.request({
//         method: 'POST',
//         url: '/api/admin/zgrada/dodaj',
//         headers: {
//             'X-Auth-Token': token,
//         },
//         body: {
//             broj: 'number',
//             brojStanova: 'numberOfApartments',
//             lokacija: 'citi',
//             ulica: 'street'
//         }
//     }).then((response) => {
//         expect(response.status).to.eq(200),
//         expect(response.body).to.have.property(broj, number)
//         expect(response.body).to.have.property(brojStanova, numberOfApartments)
//         expect(response.body).to.have.property(lokacija, citi)
//         expect(response.body).to.have.property(ulica, street)
//     })
// })