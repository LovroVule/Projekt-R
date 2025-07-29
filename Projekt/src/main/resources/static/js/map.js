let map;

// Referentni DOM elementi
const problemList          = document.getElementById("problemList");
const filterStatusCheckbox = document.getElementById("filterStatus");
const userList             = document.getElementById("userList");
const addUserForm          = document.getElementById("addUserForm");
const addLampForm          = document.getElementById("addLampForm");

window.addEventListener("DOMContentLoaded", () => {
    // Inicijalizacija mape
    map = L.map('map').setView([45.815399, 15.966568], 13);
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '© OpenStreetMap'
    }).addTo(map);

    // Učitavanje podataka
    loadLamps();
    loadUsers();

    // Event listeneri
    filterStatusCheckbox.addEventListener("change", loadLamps);
    addLampForm.addEventListener("submit", handleAddLamp);
    addUserForm.addEventListener("submit", handleAddUser);
});

// ---------- LAMPS ----------
function loadLamps() {
    const url = filterStatusCheckbox.checked ? '/lamps/notWorking' : '/lamps/all';
    // Očisti markere i listu
    map.eachLayer(layer => { if (layer instanceof L.Marker) map.removeLayer(layer); });
    problemList.innerHTML = "";

    fetch(url)
        .then(res => {
            if (!res.ok) throw new Error("Greška pri dohvaćanju lampi");
            return res.json();
        })
        .then(data => data.forEach(renderLamp))
        .catch(err => console.error(err));
}

function renderLamp(lamp) {
    // Koristimo JSON property 'addres' koji dolazi iz modela
    geocodeAddress(lamp.addres, (lat, lon) => {
        if (!lat) return;
        const icon = lamp.status === 1 ? zeleniMarker : crveniMarker;
        L.marker([lat, lon], { icon }).addTo(map)
            .bindPopup(`
            <b>Adresa:</b> ${lamp.addres}<br>
            <b>Potrošnja:</b> ${lamp.usage}W<br>
            <b>Radni sati:</b> ${lamp.workHours}H<br>
            <b>Status:</b> ${lamp.status === 1 ? "Working" : "Out of order"}
         `);

        // Dodaj u listu lampi
        const li = document.createElement("li");
        li.innerHTML = `
          <b>${lamp.addres}</b> — ${lamp.usage}W, ${lamp.workHours}H, ${lamp.status === 1 ? "Working" : "Out of order"}
          <button class="del-lamp" data-adress="${encodeURIComponent(lamp.addres)}">×</button>
        `;
        li.querySelector(".del-lamp").addEventListener("click", () => deleteLamp(lamp.addres, li));
        problemList.appendChild(li);
    });
}

function handleAddLamp(e) {
    e.preventDefault();
    const addres    = document.getElementById("adresa").value;
    const usage     = parseFloat(document.getElementById("usage").value);
    const workHours = parseFloat(document.getElementById("workHours").value);

    fetch('/lamps/add/Lamp', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ addres, usage, workHours })
    })
        .then(res => {
            if (!res.ok) throw new Error('Greška pri dodavanju lampe');
            addLampForm.reset();
            // Refresh stranice nakon dodavanja lampe
            window.location.reload();
        })
        .catch(err => alert(err.message));
}

// Za brisanje lampe
function deleteLamp(adressValue, listItem) {
    if (!confirm(`Obrisati lampu na adresi ${adressValue}?`)) return;

    fetch(`/lamps/delete?addres=${encodeURIComponent(adressValue)}`, {
        method: 'POST'  // ili 'DELETE' ako promeniš mapping na @DeleteMapping
    })
        .then(res => {
            if (!res.ok) throw new Error('Greška pri brisanju lampe');
            listItem.remove();
        })
        .catch(err => alert(err.message));
}

function geocodeAddress(address, cb) {
    fetch(`https://nominatim.openstreetmap.org/search?format=json&q=${encodeURIComponent(address)}`)
        .then(r => r.json())
        .then(arr => cb(arr[0]?.lat, arr[0]?.lon))
        .catch(() => cb(null, null));
}

const zeleniMarker = L.icon({ iconUrl: '/images/zeleniMarker.png', iconSize: [32,32], iconAnchor: [16,32], popupAnchor: [0,-32] });
const crveniMarker = L.icon({ iconUrl: '/images/crveniMarker.png', iconSize: [32,32], iconAnchor: [16,32], popupAnchor: [0,-32] });

// ---------- USERS ----------
function loadUsers() {
    fetch('/getAll/users')
        .then(res => res.json())
        .then(users => {
            userList.innerHTML = "";
            users.forEach(u => {
                const li = document.createElement('li');
                li.textContent = `${u.username} (${u.role})`;
                const btn = document.createElement('button');
                btn.textContent = '×';
                btn.addEventListener('click', () => deleteUser(u.username, li));
                li.appendChild(btn);
                userList.appendChild(li);
            });
        })
        .catch(err => console.error(err));
}

function handleAddUser(e) {
    e.preventDefault();
    const data = {
        username: document.getElementById('newUsername').value,
        password: document.getElementById('newPassword').value,
        role:     document.getElementById('newRole').value
    };
    fetch('/register/user', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
        .then(r => {
            if (!r.ok) throw new Error('Greška pri dodavanju usera.');
            addUserForm.reset();
            loadUsers();
        })
        .catch(err => alert(err.message));
}

function deleteUser(username, listItem) {
    if (!confirm(`Obrisati korisnika ${username}?`)) return;

    fetch(`/delete/user?username=${encodeURIComponent(username)}`, {
        method: 'POST'  // ili 'DELETE' ako promeniš mapping na @DeleteMapping
    })
        .then(res => {
            if (!res.ok) throw new Error('Greška pri brisanju usera');
            listItem.remove();
        })
        .catch(err => alert(err.message));
}