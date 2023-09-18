<script>
  import { onMount } from "svelte";
  import { page } from "$app/stores";
  import BattleLogEntry from "./BattleLogEntry.svelte";
  import Enemy from "./Enemy.svelte";

  /**
   * @type {any}
   */
  export let gameSession;

  /**
   * @type {any}
   */
  export let host;

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

<div class="row" style="margin-top: 70px;">
  <div class="col--12">
    {#if gameSession && gameSession.section && gameSession.section.actions}
      <div class="card border-secondary mb-12" style="margin-top: 20px;">
        <div class="card-header">{gameSession.section.sectionNumber}</div>
        <div class="card-body">
          {#if gameSession.character.endurance.value < 25}
            <div class="row">
              <div class="col-lg-12">
                <div class="alert alert-dismissible alert-danger">
                  <button type="button" class="btn-close" data-bs-dismiss="alert" />
                  <strong>Pass auf!</strong> Deine Lebenspunkte {gameSession.character.endurance.value} sind Gefährlich niedrig.
                </div>
              </div>
            </div>
          {/if}

          <div class="row">
            <div class="col-lg-12">
              <p>{@html gameSession.modifiedSectionText}</p>
            </div>
          </div>

          {#each gameSession.modifiedAnswerOptions as mao}
            {#if mao.type == "BATTLE"}
              {#each mao.battle.enemy as enemy}
                <div class="row">
                  <div class="col-4">
                    <Enemy {enemy} />
                  </div>
                  <div class="col-8">
                    {#if gameSession.battleLog}
                      {#each gameSession.battleLog as be}
                        <BattleLogEntry entry={be} gameSession={gameSession} />
                      {/each}
                    {/if}
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
  </div>
</div>
