<script>
  import { onMount } from "svelte";
  import { page } from "$app/stores";
    import Character from "./Character.svelte";

  /**
   * @type {any}
   */
  export let gameSession;

  
  /**
   * @type {any}
   */
   export let host ;

  $: info = JSON.stringify(gameSession, null, 2);
  
  $: host, (host = $page.url.hostname);

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
      </div>

      {#each gameSession.modifiedAnswerOptions as mao}
        {#if mao.type == "BATTLE"}
          {#each mao.battle.enemy as enemy}
            

            <div class="row">
              <div class="col-lg-4">
                <div class="card border-danger mb-4">
                  <div class="card-header">Gegner</div>
                  <div class="card-body">
                    <h4 class="card-title">{enemy.name}</h4>
                    <br />
                    <p class="card-text">
                      Ausdauer: <strong
                        ><span class="text-success">
                          {enemy.endurance}
                        </span></strong
                      ><br />
                      Kampfstärke:
                      <strong
                        ><span class="text-success">
                          {enemy.battleStrength}</span
                        ></strong
                      >
                    </p>
                  </div>
                </div>
              </div>
              <div class="col-lg-4"></div>
              <div class="col-lg-4">
                <div class="overflow-y-auto" style="max-height: 150px; margin-right:auto;" >
                  {#if gameSession.character.endurance.value < 25}
                  <div class="alert alert-dismissible alert-danger">
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    <strong>Pass auf!</strong> <a href="#" class="alert-link">deine Lebenspunkte sind Gefährlich niedrig</a>
                  </div>
                 {/if}
                  <p>Kampflog</p>

                </div>
              </div>
            </div>
          {/each}
        {/if}
      {/each}

      <hr />

      <div class="row">
        <div class="col-lg-7">
          <div class="bs-component">
            <div class="d-grid gap-2">
              {#if gameSession.modifiedAnswerOptions}
                {#each gameSession.modifiedAnswerOptions as action, i}
                  <button
                    type="button"
                    class="btn btn-primary"
                    on:click={() => doPost(i)}>{action.text}</button
                  >
                {/each}
              {:else}
                {#each gameSession.section.actions as action, i}
                  <button
                    type="button"
                    class="btn btn-primary"
                    on:click={() => doPost(i)}>{action.text}</button
                  >
                {/each}
              {/if}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
{/if}
