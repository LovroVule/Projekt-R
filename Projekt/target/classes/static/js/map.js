let map;

document.addEventListener("DOMContentLoaded", function () {
    map = L.map('map').setView([45.815399, 15.966568], 13); // Zagreb koordinate, default zoom 13

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '© OpenStreetMap'
    }).addTo(map);
});

function geocodeAddress(address, callback) {
    const url = `https://nominatim.openstreetmap.org/search?format=json&q=${encodeURIComponent(address)}`;
    console.log(`Geocoding URL: ${url}`);

    fetch(url)
        .then(response => response.json())
        .then(data => {
            if (data.length > 0) {
                const latitude = data[0].lat;
                const longitude = data[0].lon;
                callback(latitude, longitude);
            } else {
                callback(null, null);
            }
        })
        .catch(error => console.error("Greška prilikom geokodiranja:", error));
}

const zeleniMarker = L.icon({
    iconUrl: '/images/zeleniMarker.png',
    iconSize: [32, 32],
    iconAnchor: [16, 32],
    popupAnchor: [0, -32]
});

const crveniMarker = L.icon({
    iconUrl: '/images/crveniMarker.png',
    iconSize: [32, 32],
    iconAnchor: [16, 32],
    popupAnchor: [0, -32]
});


fetch("/problems/all")
    .then(response => {
        if (!response.ok) {
            throw new Error("Ne mogu naci");
        }
        return response.json();
    })
    .then(data => {
        data.forEach(problem => {
            const cityAdress = `${problem.adress}, Zagreb`;
            //console.log("Adresa problema:", cityAdress);
            geocodeAddress(cityAdress, (latitude, longitude) => {
                if (latitude && longitude) {

                    if (problem.status === "1") {
                        L.marker([latitude, longitude], {
                            icon: zeleniMarker
                        }).addTo(map)
                            .bindPopup(`
                                <b>Adresa:</b> ${problem.adress}<br>
                                <b>Korištenje (Usage):</b> ${problem.usage}<br>
                                <b>Radni sati (Work Hours):</b> ${problem.workHours}<br>
                            `)
                            .openPopup();
                    } else {
                        L.marker([latitude, longitude], {
                            icon: crveniMarker
                        }).addTo(map)
                            .bindPopup(`
                                <b>Adresa:</b> ${problem.adress}<br>
                                <b>Korištenje (Usage):</b> ${problem.usage}<br>
                                <b>Radni sati (Work Hours):</b> ${problem.workHours}<br>
                                <b>Opis:</b> ${problem.description}
                            `)
                            .openPopup();
                    }

                    //console.log(`Problem "${problem.description}" na koordinatama: ${latitude}, ${longitude}`);
                } else {
                    console.warn(`Problem "${problem.description}" nije prikazan jer koordinate nisu pronađene.`);
                }
            });

        });
    })
    .catch(error => console.error("Dohvat", error));


fetch("/problems/all")
    .then(response => {
        if (!response.ok) {
            throw new Error("Ne mogu naci");
        }
        return response.json();
    })
    .then(data => {
        const listContainer = document.querySelector(".div_list ol"); // Odabir liste u HTML-u
        data.forEach(problem => {
            const st = problem.status == 1 ? "Working" : "Out of order";
            const listItem = document.createElement("li");

            if(problem.status == 1){
                listItem.innerHTML = `
                    <b>Adresa:</b> ${problem.adress}<br>
                    <b>Korištenje (Usage):</b> ${problem.usage}W<br>
                    <b>Radni sati (Work Hours):</b> ${problem.workHours}H<br>
                    <b>Status:</b> ${st} <br>`
            }
            else{
                listItem.innerHTML = `
                    <b>Adresa:</b> ${problem.adress}<br>
                    <b>Korištenje (Usage):</b> ${problem.usage}W<br>
                    <b>Radni sati (Work Hours):</b> ${problem.workHours}H<br>
                    <b>Status:</b> ${st} <br>
                    <b>Opis kvara:</b> ${problem.description}`
            }
            listContainer.appendChild(listItem);
        });
    })
    .catch(error => console.error("Dohvat", error));
