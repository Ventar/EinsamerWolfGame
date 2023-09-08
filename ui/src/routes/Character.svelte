<script>
  // @ts-nocheck

  import { onMount } from "svelte";
  import { page } from "$app/stores";

  /**
   * @type {any}
   */
  export let gameSession;

  /**
   * @type {any}
   */
  export let host;
    
  
  let character = "Charakter";

  /**
   * @type {any}
   */
  let nameList;
  
  onMount(async () => {
    const res = await fetch("http://" + host + ":8080/session/list/", {
      method: "GET",
    });

    nameList = await res.json();
  });

  /**
   * @param {string} characterName
   */
  async function loadGameSession(characterName) {
    const res = await fetch("http://" + host + ":8080/session/load/", {
      method: "POST",
      body: JSON.stringify({
        name: characterName,
      }),
    });

    gameSession = await res.json();
  }

  async function saveGameSession() {
    const res = await fetch("http://" + host + ":8080/session/save/", {
      method: "POST",
      body: JSON.stringify({
        name: character,
        id: gameSession.id,
      }),
    });

    /*
   gameSession = await req.json();
   */
  }

  async function translate(key) {
   
    const res = await fetch("http://" + host + ":8080/translate/", {
      method: "POST",
      body: JSON.stringify({
        key: key
      }),
    });

    return await res.json();
   
   

  }

function percent(min, max, current) {
  return current * (100 / max);
}

</script>

{#if nameList}
  <div>
    <button type="button" class="btn btn-primary">Neuer Charakter</button>
    <div
      class="btn-group"
      role="group"
      aria-label="Button group with nested dropdown"
    >
      <button type="button" class="btn btn-primary">Lade Charakter</button>
      <div class="btn-group" role="group">
        <button
          id="btnGroupDrop1"
          type="button"
          class="btn btn-primary dropdown-toggle"
          data-bs-toggle="dropdown"
          aria-haspopup="true"
          aria-expanded="false"
        />
        <div class="dropdown-menu" aria-labelledby="btnGroupDrop1" style="">
          {#each nameList.names as name, i}
            <a class="dropdown-item" on:click={() => loadGameSession(name)}
              >{name}</a
            >
            <!--button type="button" class="btn btn-primary" on:click={() => doPost(i)}>{action.text}</button-->
          {/each}
        </div>
      </div>
    </div>
    <input bind:value={character} />
    <button
      type="button"
      class="btn btn-primary"
      on:click={() => saveGameSession()}
      >Speicher Charakter
    </button>
  </div>
  <hr />
{/if}



{#if gameSession && gameSession.character}
  <div class="row" style="padding-top: 50px;">

    <table class="table table-hover">
      <thead>
        <tr>
          <th scope="col">Typ</th>
          <th scope="col">Diagramm</th>
          <th scope="col">Anzahl</th>
          
        </tr>
      </thead>
      <tbody>
        <tr class="table-active">
          <th scope="row">Ausdauer</th>
          <td> 
            <div class="progress">
            <div
              class="progress-bar bg-success"
              role="progressbar"
              style="width: {percent(0,gameSession.character.endurance.maxValue,gameSession.character.endurance.value)}%;"
              aria-valuenow={gameSession.character.food.value}
              aria-valuemin="0"
              aria-valuemax="5"
            />
          </div></td>
          <td>{gameSession.character.endurance.value}</td>
          
        </tr>
        <tr>
          <th scope="row">Kampfstärke</th>
          <td><div class="progress">
            <div
              class="progress-bar bg-success"
              role="progressbar"
              style="width: {percent(0,gameSession.character.battleStrength.maxValue,gameSession.character.battleStrength.value)}%;"
              aria-valuenow={gameSession.character.food.value}
              aria-valuemin="0"
              aria-valuemax="5"
            />
          </div></td>
          <td>{gameSession.character.battleStrength.value}</td>
          
        </tr>
        <tr class="table-active">
          <th scope="row">Gold</th>
          <td>
            <div class="progress">
            <div
              class="progress-bar bg-success"
              role="progressbar"
              style="width: {percent(0,gameSession.character.gold.maxValue,gameSession.character.gold.value)}%;"
              aria-valuenow={gameSession.character.food.value}
              aria-valuemin="0"
              aria-valuemax="5"
            />
          </div>
        </td>
          <td>{gameSession.character.gold.value}</td>
          
        </tr>
        <tr>
          <th scope="row">Essen</th>
          <td><div class="progress">
            <div
              class="progress-bar bg-success"
              role="progressbar"
              style="width: {percent(0,gameSession.character.food.maxValue,gameSession.character.food.value)}%;"
              aria-valuenow={gameSession.character.food.value}
              aria-valuemin="0"
              aria-valuemax="5"
            />
          </div></td>
          <td>{gameSession.character.food.value}</td>
          
        </tr>
      </tbody>
    </table>

    <div class="col-lg-12" />
    <div class="card text-white bg-secondary mb-3" style="max-width: 20rem;">
      <div class="card-header">Charakter Eigenschaften</div>
      <div class="card-body">
        <h4 class="card-title" />
        <p class="card-text">
          Rucksack:
          <br />Kai-Robe<br />
          Karte<br />
          Leder-Tasche<br />
          Grüne-Tunika
        </p>
      </div>
    </div>

    <hr />
    <div class="card text-white bg-secondary mb-3" style="max-width: 20rem;">
      <div class="card-header">Skills</div>
      <div class="card-body">
        <h4 class="card-title" />
        <p class="card-text">
          {#if gameSession.character.skills}
            {#each gameSession.character.skills as skill, i}
            	{#await translate(skill)}
		            {i}. {skill}<br />
	            {:then translation}
		            {i}. {translation.value}<br/>
	            {:catch someError}
		            System error: {someError.message}.
	            {/await}
            {/each}
          {:else}
            keine<br />
          {/if}
        </p>
      </div>
    </div>


    <div class="card text-white bg-secondary mb-3" style="max-width: 20rem;">
      <div class="card-header">Essen</div>
      <div class="card-body">
        <h4 class="card-title" />
        <p class="card-text" />
        <div class="progress">
          <div
            class="progress-bar bg-success"
            role="progressbar"
            style="width: {percent(0,5,gameSession.character.food.value)}%;"
            aria-valuenow={gameSession.character.food.value}
            aria-valuemin="0"
            aria-valuemax="5"
          />
        </div>
      </div>
    </div>

    <hr />
    <div class="card text-white bg-secondary mb-3" style="max-width: 20rem;">
      <div class="card-header">Kampfstärke</div>
      <div class="card-body">
        <h4 class="card-title" />
        <p class="card-text">
          Stärke: {gameSession.character.battleStrength.value}
        </p>
      </div>

      <hr />
      <div class="card text-white bg-secondary mb-3" style="max-width: 20rem;">
        <div class="card-header">Ausdauer</div>
        <div class="card-body">
          <h4 class="card-title" />
          <p class="card-text">
            Ausdauer : {gameSession.character.endurance.value} von {gameSession
              .character.endurance.maxValue}
          </p>
        </div>
      </div>

      <hr />
      <div class="card text-white bg-secondary mb-3" style="max-width: 20rem;">
        <div class="card-header">Waffen</div>
        <div class="card-body">
          <h4 class="card-title" />
          <p class="card-text">
            Waffe-1: Breitschwert <br />
            Waffe-2: Dolch
          </p>
        </div>
      </div>
    </div>


    

  </div>
{/if}
