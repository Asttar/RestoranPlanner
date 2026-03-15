<script setup>
import twoPeopleTable from './twoPeopleTable.vue';
import fourPeopleTable from './fourPeopleTable.vue';
import sixPeopleTable from './sixPeopleTable.vue';
import eightPeopleTable from './eightPeopleTable.vue';
import tenPeopleTable from './tenPeopleTable.vue';
</script>

<template>
    <form class="card" @submit.prevent="fetchSeats()">
        <label for="area">Please enter your desired area</label>
        <select id="area" v-model="search.area">
            <option value="inside">Indoor Hall</option>
            <option value="outside">Terrace</option>
            <option value="private">Private Room</option>
        </select>

        <label for="date">Please enter the desired date and time for your reservation</label>
        <input type="date" id="date" :min="min" :max="max" v-model="search.date">

        <button type="submit">Search</button>
        <p>Hover over tables to view their bookings.</p>

    </form>

    <div>
        <p style="text-align: center;" v-if="seats.length && search.area == 'inside'">Toilet</p>
        <p style="text-align: center;" v-if="seats.length && search.area == 'outside'">View to beach</p>
        <div style="display: flex; align-items: center;">
            <p style="text-align: center;" v-if="seats.length && (search.area == 'inside' || search.area == 'private')">
                Window-side</p>
            <div class="grid-inside" v-if="seats.length && search.area == 'inside'">
                <component :title="getBooking(seat)" :is="type(seat)" :class="position(seat)" v-for="seat in seats"
                    :key="seat.id" />
                <p style="text-align: center;" class="position-1-9">Playground</p>
                <p style="text-align: center;" class="position-1-10">Playground</p>
                <p style="text-align: center;" class="position-2-9">Playground</p>
                <p style="text-align: center;" class="position-2-10">Playground</p>
            </div>
            <div class="grid-outside" v-if="seats.length && search.area == 'outside'">
                <component :title="getBooking(seat)" :is="type(seat)" :class="position(seat)"
                    :color="availability(seat)" v-for="seat in seats" :key="seat.id" />
            </div>
            <div class="grid-private" v-if="seats.length && search.area == 'private'">
                <component :title="getBooking(seat)" :is="type(seat)" :class="position(seat)"
                    :color="availability(seat)" v-for="seat in seats" :key="seat.id" />
                <p style="text-align: center;" class="position-3-4">TV</p>
            </div>
            <p style="text-align: center;" v-if="seats.length && search.area == 'outside'">View to city</p>
        </div>
        <p style="text-align: center;" v-if="seats.length && search.area == 'outside'">View to garden</p>
        <p style="text-align: center;" v-if="seats.length && search.area == 'private'">Door</p>
    </div>
    <a href="https://www.vecteezy.com/free-vector/round-table-and-chairs">Round Table And Chairs Vectors by Vecteezy</a>

</template>

<script>
export default {
    data() {
        const max = new Date()
        max.setDate(max.getDate() + 29)
        return {
            min: new Date().toISOString().split("T")[0],
            max: max.toISOString().split("T")[0],
            search: {
                date: new Date().toISOString().split("T")[0],
                area: "inside",
                name: "",
                phone: ""
            },
            seats: [],

        };
    },
    methods: {
        getBooking(seat) {
            let title = ""
            for (let i = 0; i < seat.planned_names.length; i += 4) {
                let startHour = seat.planned_times[i].split(":")[0]
                let startMinute = seat.planned_times[i].split(":")[1]

                let endHour
                let endMinute
                if (startMinute === "00") {
                    endHour = Number(startHour) + 1
                    endMinute = "59"
                } else {
                    endHour = Number(startHour) + 2
                    endMinute = "29"
                }

                title += `${seat.planned_names[i]} (${seat.planned_phones[i]}): ${startHour}:${startMinute}-${endHour}:${endMinute}\n`;
            }
            if (title === "") {
                return "No bookings on this day"
            }
            return title
        },
        position(seat) {
            let x = seat.row_table
            let y = seat.column_table

            let className = "table " + "position-" + x + "-" + y
            return className
        },
        type(seat) {
            let size = seat.size

            switch (size) {
                case 2:
                    return twoPeopleTable
                case 4:
                    return fourPeopleTable
                case 6:
                    return sixPeopleTable
                case 8:
                    return eightPeopleTable
                case 10:
                    return tenPeopleTable
            }

        },
        fetchSeats() {
            let baseUrl = "http://localhost:3000/api/tables/";
            let params = [];
            params.push(`date=${this.search.date}`);

            let url = baseUrl + this.search.area + "/view?" + (params.join("&"));

            fetch(url)
                .then((response) => response.json())
                .then((data) => {
                    this.seats = data
                })
                .catch((err) => console.error("Error fetching seats:", err.message));

        },

    }
}
</script>

<style>
.filters {
    padding: 10px;
    background-color: #007bff;
    display: flex;
    flex-direction: column;
    border-radius: 10px;
    min-width: 130px;
}

.filters>div {
    margin-bottom: 50px;
}

.time {
    display: flex;
    flex-wrap: wrap;
    gap: 8px;
    justify-content: center;
}

.time-input {
    display: none;
}

.time-label:has(.time-input:checked) {
    background-color: #007bff;
    color: white;
}

.time-label {
    padding: 6px 10px;
    border-radius: 999px;
    border: 1px solid #ccc;
    cursor: pointer;
    background-color: white;
}

.table {
    margin: 0;
    justify-self: center;
    align-self: center;
    height: 100%;
}

.position-1-1 {
    grid-row: 1;
    grid-column: 1;
}

.position-1-2 {
    grid-row: 1;
    grid-column: 2;
}

.position-1-3 {
    grid-row: 1;
    grid-column: 3;
}

.position-1-4 {
    grid-row: 1;
    grid-column: 4;
}

.position-1-5 {
    grid-row: 1;
    grid-column: 5;
}

.position-1-6 {
    grid-row: 1;
    grid-column: 6;
}

.position-1-7 {
    grid-row: 1;
    grid-column: 7;
}

.position-1-8 {
    grid-row: 1;
    grid-column: 8;
}

.position-1-9 {
    grid-row: 1;
    grid-column: 9;
}

.position-1-10 {
    grid-row: 1;
    grid-column: 10;
}

.position-2-1 {
    grid-row: 2;
    grid-column: 1;
}

.position-2-2 {
    grid-row: 2;
    grid-column: 2;
}

.position-2-3 {
    grid-row: 2;
    grid-column: 3;
}

.position-2-4 {
    grid-row: 2;
    grid-column: 4;
}

.position-2-5 {
    grid-row: 2;
    grid-column: 5;
}

.position-2-6 {
    grid-row: 2;
    grid-column: 6;
}

.position-2-7 {
    grid-row: 2;
    grid-column: 7;
}

.position-2-8 {
    grid-row: 2;
    grid-column: 8;
}

.position-2-9 {
    grid-row: 2;
    grid-column: 9;
}

.position-2-10 {
    grid-row: 2;
    grid-column: 10;
}

.position-3-1 {
    grid-row: 3;
    grid-column: 1;
}

.position-3-2 {
    grid-row: 3;
    grid-column: 2;
}

.position-3-3 {
    grid-row: 3;
    grid-column: 3;
}

.position-3-4 {
    grid-row: 3;
    grid-column: 4;
}

.position-3-5 {
    grid-row: 3;
    grid-column: 5;
}

.position-3-6 {
    grid-row: 3;
    grid-column: 6;
}

.position-3-7 {
    grid-row: 3;
    grid-column: 7;
}

.position-3-8 {
    grid-row: 3;
    grid-column: 8;
}

.position-3-9 {
    grid-row: 3;
    grid-column: 9;
}

.position-3-10 {
    grid-row: 3;
    grid-column: 10;
}

.position-4-1 {
    grid-row: 4;
    grid-column: 1;
}

.position-4-2 {
    grid-row: 4;
    grid-column: 2;
}

.position-4-3 {
    grid-row: 4;
    grid-column: 3;
}

.position-4-4 {
    grid-row: 4;
    grid-column: 4;
}

.position-4-5 {
    grid-row: 4;
    grid-column: 5;
}

.position-4-6 {
    grid-row: 4;
    grid-column: 6;
}

.position-4-7 {
    grid-row: 4;
    grid-column: 7;
}

.position-4-8 {
    grid-row: 4;
    grid-column: 8;
}

.position-4-9 {
    grid-row: 4;
    grid-column: 9;
}

.position-4-10 {
    grid-row: 4;
    grid-column: 10;
}

.position-5-1 {
    grid-row: 5;
    grid-column: 1;
}

.position-5-2 {
    grid-row: 5;
    grid-column: 2;
}

.position-5-3 {
    grid-row: 5;
    grid-column: 3;
}

.position-5-4 {
    grid-row: 5;
    grid-column: 4;
}

.position-5-5 {
    grid-row: 5;
    grid-column: 5;
}

.position-5-6 {
    grid-row: 5;
    grid-column: 6;
}

.position-5-7 {
    grid-row: 5;
    grid-column: 7;
}

.position-5-8 {
    grid-row: 5;
    grid-column: 8;
}

.position-5-9 {
    grid-row: 5;
    grid-column: 9;
}

.position-5-10 {
    grid-row: 5;
    grid-column: 10;
}

.position-6-1 {
    grid-row: 6;
    grid-column: 1;
}

.position-6-2 {
    grid-row: 6;
    grid-column: 2;
}

.position-6-3 {
    grid-row: 6;
    grid-column: 3;
}

.position-6-4 {
    grid-row: 6;
    grid-column: 4;
}

.position-6-5 {
    grid-row: 6;
    grid-column: 5;
}

.position-6-6 {
    grid-row: 6;
    grid-column: 6;
}

.position-6-7 {
    grid-row: 6;
    grid-column: 7;
}

.position-6-8 {
    grid-row: 6;
    grid-column: 8;
}

.position-6-9 {
    grid-row: 6;
    grid-column: 9;
}

.position-6-10 {
    grid-row: 6;
    grid-column: 10;
}

.position-7-1 {
    grid-row: 7;
    grid-column: 1;
}

.position-7-2 {
    grid-row: 7;
    grid-column: 2;
}

.position-7-3 {
    grid-row: 7;
    grid-column: 3;
}

.position-7-4 {
    grid-row: 7;
    grid-column: 4;
}

.position-7-5 {
    grid-row: 7;
    grid-column: 5;
}

.position-7-6 {
    grid-row: 7;
    grid-column: 6;
}

.position-7-7 {
    grid-row: 7;
    grid-column: 7;
}

.position-7-8 {
    grid-row: 7;
    grid-column: 8;
}

.position-7-9 {
    grid-row: 7;
    grid-column: 9;
}

.position-7-10 {
    grid-row: 7;
    grid-column: 10;
}

.position-8-1 {
    grid-row: 8;
    grid-column: 1;
}

.position-8-2 {
    grid-row: 8;
    grid-column: 2;
}

.position-8-3 {
    grid-row: 8;
    grid-column: 3;
}

.position-8-4 {
    grid-row: 8;
    grid-column: 4;
}

.position-8-5 {
    grid-row: 8;
    grid-column: 5;
}

.position-8-6 {
    grid-row: 8;
    grid-column: 6;
}

.position-8-7 {
    grid-row: 8;
    grid-column: 7;
}

.position-8-8 {
    grid-row: 8;
    grid-column: 8;
}

.position-8-9 {
    grid-row: 8;
    grid-column: 9;
}

.position-8-10 {
    grid-row: 8;
    grid-column: 10;
}

.position-9-1 {
    grid-row: 9;
    grid-column: 1;
}

.position-9-2 {
    grid-row: 9;
    grid-column: 2;
}

.position-9-3 {
    grid-row: 9;
    grid-column: 3;
}

.position-9-4 {
    grid-row: 9;
    grid-column: 4;
}

.position-9-5 {
    grid-row: 9;
    grid-column: 5;
}

.position-9-6 {
    grid-row: 9;
    grid-column: 6;
}

.position-9-7 {
    grid-row: 9;
    grid-column: 7;
}

.position-9-8 {
    grid-row: 9;
    grid-column: 8;
}

.position-9-9 {
    grid-row: 9;
    grid-column: 9;
}

.position-9-10 {
    grid-row: 9;
    grid-column: 10;
}

.position-10-1 {
    grid-row: 10;
    grid-column: 1;
}

.position-10-2 {
    grid-row: 10;
    grid-column: 2;
}

.position-10-3 {
    grid-row: 10;
    grid-column: 3;
}

.position-10-4 {
    grid-row: 10;
    grid-column: 4;
}

.position-10-5 {
    grid-row: 10;
    grid-column: 5;
}

.position-10-6 {
    grid-row: 10;
    grid-column: 6;
}

.position-10-7 {
    grid-row: 10;
    grid-column: 7;
}

.position-10-8 {
    grid-row: 10;
    grid-column: 8;
}

.position-10-9 {
    grid-row: 10;
    grid-column: 9;
}

.position-10-10 {
    grid-row: 10;
    grid-column: 10;
}

.green {
    background-color: green;
}

.yellow {
    background-color: yellow;
}

.orange {
    background-color: orange;
}

.red {
    background-color: red;
}



.grid-inside {
    background-color: #c4a484;
    margin: 10px;
    display: grid;
    grid-template-columns: repeat(10, 1fr);
    grid-template-rows: repeat(6, 1fr);
    grid-column-gap: 0px;
    grid-row-gap: 10px;
}

.grid-outside {
    background-color: #8d6c4c;
    margin: 10px;
    display: grid;
    grid-template-columns: repeat(5, 1fr);
    grid-template-rows: repeat(6, 1fr);
    grid-column-gap: 0px;
    grid-row-gap: 10px;
}

.grid-private {
    background-color: #c4a48476;
    margin: 10px;
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    grid-template-rows: repeat(6, 1fr);
    grid-column-gap: 0px;
    grid-row-gap: 10px;
}

.card {
    display: flex;
    flex-direction: column;
    gap: 10px;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 8px;
    background-color: #f9f9f9;
    max-width: 500px;
    margin: auto;
}

input,
select {
    padding: 8px;
    font-size: 16px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

button {
    margin-top: 10px;
    padding: 10px;
    font-size: 16px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

button:hover {
    background-color: #0056b3;
}

/* Remove number input arrows */
input[type="number"]::-webkit-inner-spin-button,
input[type="number"]::-webkit-outer-spin-button {
    -webkit-appearance: none;
    margin: 0;
}

input[type="number"] {
    -moz-appearance: textfield;
}

.item {
    list-style: none;
    margin-top: 10px;
}

.side-by-side {
    align-self: center;
    display: flex;
    gap: 5px;
}

p {
    margin: 0;
}

select:hover,
#date,
#time {
    cursor: pointer;
}
</style>
