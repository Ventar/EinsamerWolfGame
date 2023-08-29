<script>
  import { onMount } from "svelte";
  import { page } from "$app/stores";

  /**
   * @type {any}
   */
  export let gameSession;

  $: info = JSON.stringify(gameSession, null, 2);

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
<div class="row" style="padding-top: 50px;">
  <div class="col-lg-12" />
  <div class="card text-white bg-secondary mb-3" style="max-width: 20rem;">
    <div class="card-header">Charakter Eigenschaften</div>
    <div class="card-body">
      <h4 class="card-title" />
      <p class="card-text">
      
        Rucksack: 
        <br>Kai-Robe<br>
        Karte<br>
        Leder-Tasche<br>
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
        Skills: <br /> 
        	Jagd <br />
          Mentale-Defensive <br />
          Tier-Verstädnis <br>
          Suche <br>
          Sechster-Sinn

      </p>
    </div>
  </div>

  <hr />
  <div class="card text-white bg-secondary mb-3" style="max-width: 20rem;">
    <div class="card-header">Essen</div>
    <div class="card-body">
      <h4 class="card-title" />
      <p class="card-text">Essen: 0 von 5</p>
    </div>
  </div>

  <hr />
  <div class="card text-white bg-secondary mb-3" style="max-width: 20rem;">
    <div class="card-header">Kampfstärke</div>
    <div class="card-body">
      <h4 class="card-title" />
      <p class="card-text"> Stärke: 15 von 20 </p>
  </div>

  <hr />
  <div class="card text-white bg-secondary mb-3" style="max-width: 20rem;">
    <div class="card-header">Ausdauer</div>
    <div class="card-body">
      <h4 class="card-title" />
      <p class="card-text"> Ausdauer :  10 von 15 </p>
    </div>
  </div>


<hr />
<div class="card text-white bg-secondary mb-3" style="max-width: 20rem;">
  <div class="card-header">Waffen</div>
  <div class="card-body">
    <h4 class="card-title" />
    <p class="card-text"> Waffe-1: Breitschwert <br>
    Waffe-2: Dolch</p> 
  </div>
</div>
</div>
</div>


<pre>
    {info}
</pre>
