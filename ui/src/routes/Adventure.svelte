<script>
  import { onMount } from "svelte";
  import { page } from '$app/stores';
  
  /**
   * @type {any}
   */
  export let gameSession;
  
  let host = "";
  $: host,  host = $page.url.hostname;

  /**
   * @param {number} i
   */
  async function doPost(i) {
   
    const res = await fetch("http://" + host + ":8080/section/", {
      method: "POST",
      body: JSON.stringify({
        id: gameSession.id,
        action: i,
      }),
    });

    gameSession = await res.json();
    console.log(gameSession);
  }
</script>

{#if gameSession && gameSession.section && gameSession.section.actions}
  <div class="card border-secondary mb-12" style="margin-top: 20px;">
    <div class="card-header">{gameSession.section.sectionNumber}</div>
    <div class="card-body">
      <div class="row">
        <div class="col-lg-12">
          <p>{@html gameSession.modifiedSectionText}</p>
        </div>
        <hr />
      </div>

      <div class="row">
        <div class="col-lg-7">
          <div class="bs-component">
            
            <div class="d-grid gap-2">
              {#if gameSession.modifiedAnswerOptions}
                {#each gameSession.modifiedAnswerOptions as action, i}
                  <button type="button" class="btn btn-primary" on:click={() => doPost(i)}>{action.text}</button>
                {/each}
              {:else}
                {#each gameSession.section.actions as action, i}
                  <button type="button" class="btn btn-primary" on:click={() => doPost(i)}>{action.text}</button>
                {/each}
              {/if}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
{/if}
