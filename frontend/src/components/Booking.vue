<script setup>
import twoPeopleTable from './twoPeopleTable.vue';
import fourPeopleTable from './fourPeopleTable.vue';
import sixPeopleTable from './sixPeopleTable.vue';
import eightPeopleTable from './eightPeopleTable.vue';
import tenPeopleTable from './tenPeopleTable.vue';
</script>

<template>

  <div class="side-by-side" id="booking-details">

    <div class="filters" v-if="seats.length">
      <p class="green">Available seats</p>
      <p class="yellow">Chosen seats</p>
      <p class="orange">Seats booked in the next 2 hours</p>
      <p class="red">Booked seats</p>

      <div v-if="search.area === 'inside'">
        <label for="window">Near window</label>
        <input id="window" type="checkbox" v-model="inside.window">
      </div>
      <div v-if="search.area === 'inside'">
        <label for="door">Near door</label>
        <input id="door" type="checkbox" v-model="inside.door">
      </div>
      <div v-if="search.area === 'inside'">
        <label for="exit">Near playground</label>
        <input id="playground" type="checkbox" v-model="inside.playground">
      </div>

      <div v-if="search.area === 'outside'">
        <label for="beach">View to beach</label>
        <input id="beach" type="checkbox" v-model="outside.beach">
      </div>
      <div v-if="search.area === 'outside'">
        <label for="city">View to city</label>
        <input id="city" type="checkbox" v-model="outside.city">
      </div>
      <div v-if="search.area === 'outside'">
        <label for="garden">View to garden</label>
        <input id="garden" type="checkbox" v-model="outside.garden">
      </div>

      <div v-if="search.area === 'private'">
        <label for="tv">Near tv</label>
        <input id="tv" type="checkbox" v-model="private.tv">
      </div>
      <div v-if="search.area === 'private'">
        <label for="door">Near door</label>
        <input id="door" type="checkbox" v-model="private.door">
      </div>
      <div v-if="search.area === 'private'">
        <label for="window">Near window</label>
        <input id="window" type="checkbox" v-model="private.window">
      </div>
      <button @click="bookTable()">Book table</button>
    </div>

    <form class="card" @submit.prevent="fetchSeats(false)">
      <label for="area">Please enter your desired area</label>
      <select id="area" v-model="search.area">
        <option value="inside">Indoor Hall</option>
        <option value="outside">Terrace</option>
        <option value="private">Private Room</option>
      </select>

      <label for="date">Please enter the desired date and time for your reservation</label>
      <input type="date" id="date" :min="min" :max="max" v-model="search.date">
      <div class="time">
        <label class="time-label" v-for="time in times" :key="time">
          <input class="time-input" type="radio" :value="time.label" v-model="search.time">
          {{ time.label }}
        </label>

      </div>


      <label for="booking-details">Please enter your name, phone number and number of people</label>
      <div class="side-by-side" id="booking-details">
        <input type="text" placeholder="Name" required v-model="search.name">
        <input type="number" placeholder="5555 5555" required v-model="search.phone">
        <select id="number-of-people" v-model="search.number_of_people">
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5</option>
          <option value="6">6</option>
          <option value="7">7</option>
          <option value="8">8</option>
          <option value="9">9</option>
          <option value="10">10</option>
        </select>
      </div>

      <button type="submit">Search</button>



    </form>
    <div class="filters" v-if="seats.length">
      <p>This day's special is {{ daily_special }}</p>
      <img :src="daily_special_img" alt="Daily special">
    </div>
  </div>


  <div>
    <p style="text-align: center;" v-if="seats.length && search.area == 'inside'">Toilet</p>
    <p style="text-align: center;" v-if="seats.length && search.area == 'outside'">View to beach</p>
    <div style="display: flex; align-items: center;">
      <p style="text-align: center;" v-if="seats.length && (search.area == 'inside' || search.area == 'private')">
        Window-side</p>
      <div class="grid-inside" v-if="seats.length && search.area == 'inside'">
        <component :is="type(seat)" :class="position(seat)" :color="availability(seat)" v-for="seat in seats"
          :key="seat.id" />
        <p style="text-align: center;" class="position-1-9">Playground</p>
        <p style="text-align: center;" class="position-1-10">Playground</p>
        <p style="text-align: center;" class="position-2-9">Playground</p>
        <p style="text-align: center;" class="position-2-10">Playground</p>
      </div>
      <div class="grid-outside" v-if="seats.length && search.area == 'outside'">
        <component :is="type(seat)" :class="position(seat)" :color="availability(seat)" v-for="seat in seats"
          :key="seat.id" />
      </div>
      <div class="grid-private" v-if="seats.length && search.area == 'private'">
        <component :is="type(seat)" :class="position(seat)" :color="availability(seat)" v-for="seat in seats"
          :key="seat.id" />
        <p style="text-align: center;" class="position-3-4">TV</p>
      </div>
      <p style="text-align: center;" v-if="seats.length && search.area == 'outside'">View to city</p>
    </div>
    <p style="text-align: center;" v-if="seats.length && search.area == 'outside'">View to garden</p>
    <p style="text-align: center;" v-if="seats.length && search.area == 'private'">Door</p>
  </div>

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
        number_of_people: 1,
        date: new Date().toISOString().split("T")[0],
        time: "11:00",
        area: "inside",
        name: "",
        phone: ""
      },
      seats: [],
      inside: {
        window: false,
        door: false,
        playground: false
      },
      outside: {
        beach: false,
        city: false,
        garden: false
      },
      private: {
        tv: false,
        door: false,
        window: false
      },
      daily_special: "",
      daily_special_img: ""
    };
  },
  computed: {
    times() {
      return this.getTimes()
    }
  },
  watch: {
    'inside.window'() {
      this.fetchSeats(true)
    },
    'inside.door'() {
      this.fetchSeats(true)
    },
    'inside.playground'() {
      this.fetchSeats(true)
    },

    'outside.beach'() {
      this.fetchSeats(true)
    },
    'outside.city'() {
      this.fetchSeats(true)
    },
    'outside.garden'() {
      this.fetchSeats(true)
    },

    'private.tv'() {
      this.fetchSeats(true)
    },
    'private.door'() {
      this.fetchSeats(true)
    },
    'private.window'() {
      this.fetchSeats(true)
    },
  },
  methods: {
    isWeekEndDay() {
      let date = new Date(this.search.date)
      let day = date.getDay()
      if (day === 0 || day === 6) {
        if (this.search.time === "09:00" || this.search.time === "09:30" || this.search.time === "10:00" || this.search.time === "10:30" || this.search.time === "19:30" || this.search.time === "20:00") {
          this.search.time = "11:00"
        }
        return true
      }
      return false
    },
    getTimes() {
      if (this.isWeekEndDay()) {
        return [{ label: "11:00" }, { label: "11:30" }, { label: "12:00" }, { label: "12:30" }, { label: "13:00" }, { label: "13:30" }, { label: "14:00" }, { label: "14:30" }, { label: "15:00" }, { label: "15:30" }, { label: "16:00" }, { label: "16:30" }, { label: "17:00" }, { label: "17:30" }, { label: "18:00" }, { label: "18:30" }, { label: "19:00" }]
      }
      return [{ label: "09:00" }, { label: "09:30" }, { label: "10:00" }, { label: "10:30" }, { label: "11:00" }, { label: "11:30" }, { label: "12:00" }, { label: "12:30" }, { label: "13:00" }, { label: "13:30" }, { label: "14:00" }, { label: "14:30" }, { label: "15:00" }, { label: "15:30" }, { label: "16:00" }, { label: "16:30" }, { label: "17:00" }, { label: "17:30" }, { label: "18:00" }, { label: "18:30" }, { label: "19:00" }, { label: "19:30" }, { label: "20:00" }, { label: "20:30" }, { label: "21:00" }]
    },

    position(seat) {
      let x = seat.row_table
      let y = seat.column_table

      let className = "table " + "position-" + x + "-" + y
      return className
    },
    availability(seat) {
      let available = seat.available
      let availability = seat.availability
      let chosen = seat.chosen

      if (chosen === true) {
        return "yellow"
      } else if (available === true) {
        return "green"
      } else if (availability === "unavailable-in-next-2-hours") {
        return "orange"
      } else {
        return "red"
      }
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
    fetchSeats(fromFilters) {
      if (!this.search.name || !this.search.phone) {
        return
      }
      let baseUrl = "http://localhost:3000/api/tables/";
      let params = [];
      params.push(`date=${this.search.date}`);
      params.push(`time=${this.search.time}`);
      params.push(`people=${this.search.number_of_people}`);

      if (this.search.area === "inside") {
        params.push(`window=${this.inside.window}`);
        params.push(`door=${this.inside.door}`);
        params.push(`playground=${this.inside.playground}`);
      }
      if (this.search.area === "outside") {
        params.push(`beach=${this.outside.beach}`);
        params.push(`city=${this.outside.city}`);
        params.push(`garden=${this.outside.garden}`);
      }
      if (this.search.area === "private") {
        params.push(`tv=${this.private.tv}`);
        params.push(`door=${this.private.door}`);
        params.push(`window=${this.private.window}`);
      }

      let url = baseUrl + this.search.area + "?" + (params.join("&"));

      fetch(url)
        .then((response) => response.json())
        .then((data) => {
          this.seats = data
          let somethingMatched = this.seats.some(table => table.chosen === true);
          if (!somethingMatched) {
            if (fromFilters) {
              alert("None of the tables matched your search, please change filters and try again.")
              this.inside.window = false
              this.inside.door = false
              this.inside.playground = false
            } else {
              alert("No available tables on this data and time for this amount of people. Please try a different data and time or reduce number of people.")
              this.seats = []
            }
          } else {
            let apiUrl = "https://www.themealdb.com/api/json/v1/1/filter.php?c=Seafood"
            let startDay = new Date(this.min)
            let currentDay = new Date(this.search.date)

            let difference = currentDay - startDay
            let differenceDays = difference / (1000 * 60 * 60 * 24);
            fetch(apiUrl)
              .then((response) => response.json())
              .then((data) => {
                this.daily_special_img = data.meals[differenceDays].strMealThumb
                this.daily_special = data.meals[differenceDays].strMeal
              })
              .catch((err) => console.error("Error fetching daily special:", err.message));
          }
        })
        .catch((err) => console.error("Error fetching seats:", err.message));

    },
    bookTable() {
      let seatId = this.seats.find(seat => seat.chosen === true).id
      let baseUrl = "http://localhost:3000/api/tables/";
      let params = [];
      params.push(`id=${seatId}`);
      params.push(`time=${this.search.time}`);
      params.push(`name=${this.search.name}`);
      params.push(`phone=${this.search.phone}`);

      let url = baseUrl + this.search.area + "/book?" + (params.join("&"));

      fetch(url, {
        method: "PUT"
      })
        .then(response => {
          if (!response.ok) throw new Error();
          alert("Successfully booked under " + this.search.name);
          window.location.reload();
        })
        .catch((err) => console.error("Error fetching seats", err.message));

    },

  }
}
</script>

<style>
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

.filters {
  padding: 10px;
  background-color: #007bff;
  display: flex;
  flex-direction: column;
  border-radius: 10px;
  min-width: 130px;
  max-width: 270px;
}

.filters>div {
  margin-bottom: 50px;
}

input[type="checkbox"] {
  transform: scale(2);
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
  margin-right: 5px;
  width: 50%;
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



.grid {
  background-color: #c4a484;
  margin: 10px;
  display: grid;
  grid-template-columns: repeat(10, 1fr);
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

.side-by-side {
  align-self: center;
  display: flex;
  gap: 5px;
}

p {
  margin: 0;
}

img {
  margin-right: 5px !important;
  width: 50% !important;
}

.date_and_time {
  display: flex;
}



select:hover,
#date,
#time {
  cursor: pointer;
}

</style>
