<script>
// @ts-nocheck

  import { onMount } from "svelte";
  import { page } from "$app/stores";

  /**
   * @type {any}
   */
  export let gameSession;

  let host = "";
  let character = "Charakter";
 

  /**
   * @type {any}
   */
  let nameList;
  $: host, (host = $page.url.hostname);

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
              {i}. {skill}<br />
            {/each}
          {:else}
            keine<br />
          {/if}
        </p>
      </div>
    </div>

    

   {#if gameSession.character.food.value == 0}
   <div class="card text-white bg-secondary mb-3" style="max-width: 20rem;">
    <div class="card-header">Essen</div>
    <div class="card-body">
      <h4 class="card-title" />
      <p class="card-text">
        Kein Essen vorhanden
      </p>
    </div>
  </div>
    
{/if}

{#if gameSession.character.food.value == 1}
<div class="card text-white bg-secondary mb-3" style="max-width: 20rem;">
  <div class="card-header">Essen</div>
  <div class="card-body">
    <h4 class="card-title" />
    <p class="card-text"> <div class="progress">
      <div class="progress-bar bg-success" role="progressbar" style="width: 20%;" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"></div>
    </div>
  
  </div>
</div>
{/if}

{#if gameSession.character.food.value == 2}
<div class="card text-white bg-secondary mb-3" style="max-width: 20rem;">
  <div class="card-header">Essen</div>
  <div class="card-body">
    <h4 class="card-title" />
    <p class="card-text"> <div class="progress">
      <div class="progress-bar bg-success" role="progressbar" style="width: 40%;" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"></div>
    </div>
  
  </div>
</div>
{/if}

{#if gameSession.character.food.value == 3}
<div class="card text-white bg-secondary mb-3" style="max-width: 20rem;">
  <div class="card-header">Essen</div>
  <div class="card-body">
    <h4 class="card-title" />
    <p class="card-text"> <div class="progress">
      <div class="progress-bar bg-success" role="progressbar" style="width: 60%;" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
    </div>
  
  </div>
</div>
{/if}

{#if gameSession.character.food.value == 4}
<div class="card text-white bg-secondary mb-3" style="max-width: 20rem;">
  <div class="card-header">Essen</div>
  <div class="card-body">
    <h4 class="card-title" />
    <p class="card-text"> <div class="progress">
      <div class="progress-bar bg-success" role="progressbar" style="width: 80%;" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"></div>
    </div>
  
  </div>
</div>
{/if}

{#if gameSession.character.food.value == 5}
<div class="card text-white bg-secondary mb-3" style="max-width: 20rem;">
  <div class="card-header">Essen</div>
  <div class="card-body">
    <h4 class="card-title" />
    <p class="card-text"> <div class="progress">
      <div class="progress-bar bg-success" role="progressbar" style="width: 100%;" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
    </div>
  
  </div>
</div>
{/if}

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
