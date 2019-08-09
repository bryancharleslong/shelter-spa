export default function Cages(cages) {
    return `
        <ul>
            ${cages.map(cage => {
                let petList = '';
                if(cage.pets != null){
                    petList = cage.pets.map(pet => pet.petName).join(', ')
                }
                return `
                    <li>
                        <h3>Cage: ${cage.cageName}</h3>
                        <a>${petList}</a>
                    </li>
                `;
    }).join('')}
        </ul>
        <section class='add-cage'>
            <button class='add-cage__submit'>+ Cage</button>
            <button class='remove-cage__submit'>- Cage</button>
        </section>
    `
}