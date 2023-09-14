<script>
  // @ts-nocheck

  import { onMount, afterUpdate, tick } from "svelte";
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

  const initpopup = (c) => {
    //console.log(c);
    new bootstrap.Popover(c,{html: true});
  };

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
  }

  async function translate(keyList) {
    const res = await fetch("http://" + host + ":8080/translate/", {
      method: "POST",
      body: JSON.stringify({
        keys: keyList,
      }),
    });

    return await res.json();
  }

  function percent(min, max, current) {
    return current * (100 / max);
  }
</script>

<div class="bs-docs-section">
  {#if nameList}
    <div class="row" style="margin-top: 70px;">
      <div class="col-lg-12">
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
    </div>
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
                  style="width: {percent(
                    0,
                    gameSession.character.endurance.maxValue,
                    gameSession.character.endurance.value
                  )}%;"
                  aria-valuenow={gameSession.character.food.value}
                  aria-valuemin="0"
                  aria-valuemax="5"
                />
              </div></td
            >
            <td>{gameSession.character.endurance.value}</td>
          </tr>
          <tr>
            <th scope="row">Kampfstärke</th>
            <td
              ><div class="progress">
                <div
                  class="progress-bar bg-success"
                  role="progressbar"
                  style="width: {percent(
                    0,
                    gameSession.character.battleStrength.maxValue,
                    gameSession.character.battleStrength.value
                  )}%;"
                  aria-valuenow={gameSession.character.food.value}
                  aria-valuemin="0"
                  aria-valuemax="5"
                />
              </div></td
            >
            <td>{gameSession.character.battleStrength.value}</td>
          </tr>
          <tr class="table-active">
            <th scope="row">Gold</th>
            <td>
              <div class="progress">
                <div
                  class="progress-bar bg-success"
                  role="progressbar"
                  style="width: {percent(
                    0,
                    gameSession.character.gold.maxValue,
                    gameSession.character.gold.value
                  )}%;"
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
            <td
              ><div class="progress">
                <div
                  class="progress-bar bg-success"
                  role="progressbar"
                  style="width: {percent(
                    0,
                    gameSession.character.food.maxValue,
                    gameSession.character.food.value
                  )}%;"
                  aria-valuenow={gameSession.character.food.value}
                  aria-valuemin="0"
                  aria-valuemax="5"
                />
              </div></td
            >
            <td>{gameSession.character.food.value}</td>
          </tr>
        </tbody>
      </table>
    </div>

    <div class="row" style="padding-top: 50px;">
      <div class="col-4">
        Kai Fähigkeiten
        <hr />
        <div class="bs-component">
          <div class="list-group">
            {#if gameSession.character.skills}
              {#await translate(gameSession.character.skills)}
                {#each gameSession.character.skills as skill}
                  {skill}<br />
                {/each}
              {:then translation}
                {#each translation.translations as t,i}
                  <li
                    class="list-group-item d-flex justify-content-between align-items-center"
                  >
                  

                    {t.de}
                    <button 
                    use:initpopup={this}
                    type="button"
                    class="btn btn-dark btn-sm"
                    data-bs-container="body"
                    data-bs-toggle="popover"
                    data-bs-trigger="focus"
                    data-bs-placement="right"
                    data-bs-content="{t.tooltip}"
                    data-bs-original-title="{t.de}"><img width="16px" src="info-16.png" alt="Deko 1" /></button
                  >
                  </li>

                  <!--button
                  type="button"
                  class="btn btn-secondary btn-sm btn-block"
                  data-bs-toggle="tooltip"
                  data-bs-placement="right"
                  title={t.tooltip}>{t.de}</button
            -->
                {/each}
              {:catch someError}
                {#each gameSession.character.skills as skill, i}
                  {i}. {skill}<br />
                {/each}
              {/await}
            {:else}
              keine<br />
            {/if}
          </div>
        </div>
      </div>

      <div class="col-4">col-sm-4</div>
      <div class="col-4">col-sm-4</div>
    </div>
  {/if}
</div>
