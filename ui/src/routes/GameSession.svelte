<script>
  // @ts-nocheck

  import { onMount } from "svelte";
  import InfoPopup from "./InfoPopup.svelte";
  import { page } from "$app/stores";
  /**
   *
   * @type {any}
   */
  export let gameSession;
  let host = "";

  $: info = JSON.stringify(gameSession, null, 2);

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

  async function translate(keyList) {
    if (Array.isArray(keyList)) {
      const res = await fetch("http://" + host + ":8080/translate/", {
        method: "POST",
        body: JSON.stringify({
          keys: keyList,
        }),
      });
      return await res.json();
    } else {
      const res = await fetch("http://" + host + ":8080/translate/", {
        method: "POST",
        body: JSON.stringify({
          keys: [keyList],
        }),
      });
      return await res.json();
    }
  }

  function lostAttribute(current, max) {
    let value = max - current;
    return { length: value };
  }

  function currentAttribute(current) {
    return { length: current };
  }
</script>

{#if gameSession && gameSession.character}
  <div class="row" style="margin-top: 90px;">
    <div class="col-3">
      <div class="bs-component">
        <div class="card text-white bg-dark mb-3" style="max-width: 20rem;">
          <div class="card-header">Ausdauer</div>

          <div class="text-center" style="margin: 10px;">
            <img
              width="30%"
              height="30%"
              src="data:image/png;base64,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"
              alt="icon" />
              <p>29</p>
          </div>
            <div class="card-body" style="display: flex; flex-wrap:wrap">
              {#each currentAttribute(12) as _, i}
                <div class="rcorners1" />
              {/each}
              {#each lostAttribute(12, 50) as _, i}
                <div class="rcorners2" />
              {/each}
            </div>
          
        </div>
      </div>
    </div>
    <div class="col-3">B</div>
    <div class="col-3">C</div>
    <div class="col-3">D</div>
  </div>
{/if}

<!--

          <li class="list-group-item">Cras justo odio</li>
          <li class="list-group-item">Dapibus ac facilisis in</li>
          <li class="list-group-item">Vestibulum at eros</li>
        </ul>
        <div class="card-body">
          <a href="#" class="card-link">Card link</a>
          <a href="#" class="card-link">Another link</a>
        </div>
        <div class="card-footer text-muted">
          2 days ago
        </div>
      </div>
      <div class="card">
        <div class="card-body">
          <h4 class="card-title">Card title</h4>
          <h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
          <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
          <a href="#" class="card-link">Card link</a>
          <a href="#" class="card-link">Another link</a>
        </div>
      </div>
    <button class="source-button btn btn-primary btn-xs" type="button" tabindex="0"><i class="bi bi-code"></i></button></div>
  </div>
</div>



-->

<div class="row" style="margin-top: 70px;">
  <div class="col-lg-12">
    <button
      type="button"
      class="btn btn-secondary"
      data-bs-container="body"
      data-bs-toggle="popover"
      data-bs-placement="right"
      data-bs-content="Vivamus sagittis lacus vel augue laoreet rutrum faucibus."
      data-bs-original-title="Popover Title">Right</button>

    <span data-toggle="tooltip" title="Some tooltip text!">Hover over me</span>

    <!--div class="overflow-y-auto" style="max-height: 600px;"-->
    <button type="submit" class="btn btn-primary">
      <i class="bi bi-cloud-arrow-up-fill" /> Speichern
    </button>

    <button type="submit" class="btn btn-primary">
      <i class="bi bi-cloud-arrow-down-fill" /> Laden
    </button>

    <button type="submit" class="btn btn-primary">
      <i class="bi bi-cloud-check-fill" /> Neues Abenteuer
    </button>

    <div class="btn-group mr-2" role="group" aria-label="Basic example">
      <button type="button" class="btn btn-secondary">Left</button>
      <button type="button" class="btn btn-secondary">Middle</button>
      <button type="button" class="btn btn-secondary">Right</button>
    </div>

    <pre>
    {info}
  </pre>
  </div>
</div>

<style>
  .rcorners1 {
    border-radius: 2px;
    background: #73ad21;
    margin: 2px;
    width: 15px;
    height: 15px;
  }
  .rcorners2 {
    border-radius: 2px;
    background: #555555;
    margin: 2px;
    width: 15px;
    height: 15px;
  }
</style>
