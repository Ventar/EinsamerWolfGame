<script>
  import { onMount } from "svelte";
  import { page } from "$app/stores";

  /**
   * @type {any}
   */
  export let gameSession;

  $: info = JSON.stringify(gameSession, null, 2);

  let host = "";

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
   * @param {string} i
   */
   async function loadGameSession(i) {
   

   const res = await fetch("http://" + host + ":8080/session/load/", {
     method: "POST",
     body: JSON.stringify({
       name: i
     }),
   });

   gameSession = await res.json();
   
 }
 let character = 'Charakter';
  /**
   * @param {string} i
   * 
   */
   async function saveGameSession(i) {
   

   const res = await fetch("http://" + host + ":8080/session/save/", {
     method: "POST",
     body: JSON.stringify({
       name: character,
       id: i
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
            <a class="dropdown-item" on:click={() => loadGameSession(name)}>{name}</a>
            <!--button type="button" class="btn btn-primary" on:click={() => doPost(i)}>{action.text}</button-->
          {/each}
        </div>
      </div>
    </div>
    <button type="button" class="btn btn-primary" on:click={() => saveGameSession(character)}>Speicher Charakter
    </button>
    <input bind:value={character}>
  </div>
  <hr />
{/if}
<pre>
    {info}
</pre>
