document.addEventListener("DOMContentLoaded", function() {
    // Provjeri postoji li element s ID-jem 'map'
    const mapElement = document.getElementById('map');
    if (!mapElement) {
        console.error("Element s ID 'map' nije pronađen. Provjeri HTML.");
        return;
    }

    // Inicijalizacija Leaflet karte
    const map = L.map('map').setView([45.815399, 15.966568], 13); // Zagreb kao početna lokacija

    // Dodavanje OpenStreetMap sloja
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);

    // Dodavanje markera na mapu
    const marker = L.marker([45.815399, 15.966568]).addTo(map)
        .bindPopup("Lokacija problema je ovdje.")
        .openPopup();

    // Ako želiš automatski dodati marker prema adresi, dodaj funkciju za geokodiranje ovdje.
});
