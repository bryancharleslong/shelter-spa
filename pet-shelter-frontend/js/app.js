import Header from './components/Header'
import Footer from './components/Footer'
import Home from './components/Home'
import Cages from './components/Cages'
import Pets from './components/Pets'
import apiActions from './api/api-actions';
import '../css/styles.css';
//var apiPath = 'http://localhost:5000';
var apiPath = 'http://localhost:5000';
main()

function main() {
    header()
    navHome()
    navCages()
    navPets()
    footer()
}
function header() {
    const header = document.querySelector('#header');
    header.innerHTML = Header();
}
function footer() {
    const footer = document.querySelector('#footer');
    footer.innerHTML = Footer();
}
function navHome() {
    const homeButton = document.querySelector('.nav__home');
    homeButton.addEventListener('click', function () {
        getAppContext().innerHTML = Home();
    })
    getAppContext().innerHTML = Home();
}
function navCages() {
    const cagesButton = document.querySelector('.nav__cages');
    //get request
    cagesButton.addEventListener('click', function () {
        apiActions.getRequest(apiPath + '/cages', cages => {
            getAppContext().innerHTML = Cages(cages);
        })
    })
    //post request
    getAppContext().addEventListener('click', function () {
        if (event.target.classList.contains('add-cage__submit')) {
            apiActions.postRequest(apiPath + '/cages/add-cage', {
            }, (cages) => getAppContext().innerHTML = Cages(cages))
        }
    })
    getAppContext().addEventListener('click', function () {
        if (event.target.classList.contains('remove-cage__submit')) {
            apiActions.postRequest(apiPath + '/cages/remove-cage', {
            }, (cages) => getAppContext().innerHTML = Cages(cages))
        }
    })

}
function navPets() {
    const petsButton = document.querySelector('.nav__pets');
    petsButton.addEventListener('click', function () {
        apiActions.getRequest(apiPath + '/cages', cages => {
            apiActions.getRequest(apiPath + '/pets', pets => {
                getAppContext().innerHTML = Pets(pets, cages);
                //after app content is loaded, start hover listener
                getAppContext().querySelector("ul").addEventListener('mouseleave', function () {
                    var oldButton = document.querySelector('#delete-button');
                        if (oldButton != null) {
                            oldButton.remove();
                        }
                })
            })
        })
    })
    getAppContext().addEventListener('click', function (event) {
        if (event.target.classList.contains('add-pet__submit')) {
            const petName = event.target.parentElement.querySelector('.add-pet__petName').value;
            const cageId = event.target.parentElement.querySelector('.add-pet__cageName').value;
            apiActions.getRequest(apiPath + '/cages', cages => {
                apiActions.postRequest(apiPath + '/pets/add-pet', {
                    petName: petName,
                    cageId: cageId
                }, (pets) => getAppContext().innerHTML = Pets(pets, cages))
            })
        }
    })
    
    //delete a pet: delete button appears on hover
    getAppContext().addEventListener('mouseover', function () {
        if (event.target.classList.contains('pet-name')) {
            var oldButton = document.querySelector('#delete-button');
            if (oldButton != null) {
                oldButton.remove();
            }
            var deleteButton = document.createElement('button');
            deleteButton.id = 'delete-button';
            deleteButton.innerHTML = 'Adopt out pet';
            event.target.parentElement.appendChild(deleteButton);
            deleteButton.addEventListener('click', function () {
                var petId = event.target.parentElement.getAttribute('petId');
                apiActions.getRequest(apiPath + '/cages', cages => {
                    apiActions.postRequest(apiPath + '/pets/remove-pet', {
                       petId: petId
                    }, (pets) => getAppContext().innerHTML = Pets(pets, cages))
                })
            })
        }
    })
}

function getAppContext() {
    return document.querySelector('#app');
}