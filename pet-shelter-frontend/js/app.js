import Header from './components/Header'
import Footer from './components/Footer'
import Home from './components/Home'
import Cages from './components/Cages'
import Pets from './components/Pets'
import apiActions from './api/api-actions';
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
        document.querySelector('#app').innerHTML = Home();
    })
}
function navCages() {
    const cagesButton = document.querySelector('.nav__cages');
    //get request
    cagesButton.addEventListener('click', function () {
        apiActions.getRequest('http://localhost:8080/cages', cages => {
            document.querySelector('#app').innerHTML = Cages(cages);
        })
    })
    //post request
    const app = document.querySelector('#app');
    app.addEventListener('click', function () {
        if (event.target.classList.contains('add-cage__submit')) {
            apiActions.postRequest('http://localhost:8080/cages/add-cage', {
            }, (cages) => app.innerHTML = Cages(cages))
        }
    })
    app.addEventListener('click', function(){
        if (event.target.classList.contains('remove-cage__submit')) {
            apiActions.postRequest('http://localhost:8080/cages/remove-cage', {
            }, (cages) => app.innerHTML = Cages(cages))
        }
    })

}
function navPets() {
    const petsButton = document.querySelector('.nav__pets');
    petsButton.addEventListener('click', function () {
        apiActions.getRequest('http://localhost:8080/cages', cages => {
            apiActions.getRequest('http://localhost:8080/pets', pets => {
                document.querySelector('#app').innerHTML = Pets(pets, cages);
            })
        })
    })

    const app = document.querySelector('#app');
    app.addEventListener('click', function () {
        if (event.target.classList.contains('add-pet__submit')) {
            const petName = event.target.parentElement.querySelector('.add-pet__petName').value;
            const cageId = event.target.parentElement.querySelector('.add-pet__cageName').value;
            apiActions.getRequest('http://localhost:8080/cages', cages => {
                apiActions.postRequest('http://localhost:8080/pets/add-pet', {
                    petName: petName,
                    cageId: cageId
                }, (pets) => app.innerHTML = Pets(pets, cages))
            })
        }
    })

}