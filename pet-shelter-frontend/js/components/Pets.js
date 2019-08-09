export default function Pets(pets, cages) {
    return `
        <ul>
            ${pets.map(pet => {
                return `
                <li>
                    <h3>${pet.petName}</h3>
                </li>
                `;
            }).join('')}
        </ul>
        <section class='add-pet'>
        <input class='add-pet__petName' type='text' placeholder='Pet Name'>
        <a> Cage: </a>
        <select class='add-pet__cageName'>
            ${cages.map(cage => {
                return `
                <option value=${cage.id}>${cage.cageName}</option>
                `
            })}
        </select>
        <button class='add-pet__submit'>Add Pet</button>

        </section>
    `
}