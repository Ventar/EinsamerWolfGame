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
  export let enemy;

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
          {#if gameSession.character.endurance.value < 8 && gameSession.character.endurance.value > 0}
            <div class="row">
              <div class="col-lg-12">
                <div class="alert alert-dismissible alert-danger">
                  <button type="button" class="btn-close" data-bs-dismiss="alert" />
                  <strong>Pass auf!</strong> Deine Lebenspunkte {gameSession.character.endurance.value} sind Gefährlich niedrig, du solltest einen Laumspr Trank
                  trinken.
                </div>
              </div>
            </div>
          {/if}

          <div class="row">
            <div class="col-lg-12">
              <p>{@html gameSession.modifiedSectionText}</p>
            </div>
          </div>

          <hr />

          <div class="row">
            <div class="col-3"/>
            <div class="col-6">
              <div class="bs-component">
                <div class="d-grid gap-2">
                  {#each gameSession.modifiedAnswerOptions as action, i}
                    <button type="button" class="btn btn-primary" on:click={() => doPost(i)}>{action.text}</button>
                  {/each}
                </div>
              </div>
            </div>
            <div class="col-3"/>
          </div>

          {#each gameSession.modifiedAnswerOptions as mao}
            {#if mao.type == "BATTLE"}
              <hr />
              <div class="row">
                <div class="col-4">
                  {#each mao.battle.enemy as enemy}
                    <Enemy {enemy} />
                  {/each}
                </div>
                <div class="col-8">
                  {#if gameSession.battleLog}
                    {#each gameSession.battleLog as be}
                      <BattleLogEntry entry={be} {gameSession} />
                    {/each}
                  {/if}
                </div>
              </div>
            {/if}
          {/each}
        </div>
      </div>
    {/if}
  </div>
</div>
