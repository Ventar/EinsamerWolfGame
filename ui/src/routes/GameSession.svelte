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
              src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAAAXNSR0IArs4c6QAABAhJREFUeF7tm0moHUUUhr8fcePCAQPuXEVdJKg4BRUhiWJUorhwF8QBHDeKCyGYqElcCCLoKoOIA+pKEIwTwWAW4hCioqigoggiCGpEF25ETjhQ99F5+l4NXT1cXhdcHo8+dc5/vj5Vt6q6r1jhTSs8fyYANSrAzO4EbgbODJ9vAP/8AByU9EZpHDPbDFwNrAbOCn9/BH4CXpK0r9S392tVAWZ2HbATuDAiYj+wLwdESNzBXh/x/QnwsKS3SkAUAzCzR4FHMoO+Ctwi6e+l+pnZScALwE2ZvndIck1ZrQhAYfIzYe/6cJH0y2KlZnYG8GIo+axEgnE2hGwAoTS9pNu0I8AWSd/OnJiZj++XgYvbOPYhkzPUSgC8njAuU3I4IGlTA4CP4WtTOsbmG0k3pPrJAlDp7je13SNpj5lt8Rk9VXSCXXIV5AKodfdnOXwvabWZ+Vfm2QmJpZrsT62CXAC/AqtSVSTa7Sj4Nom5Pirp9JiRX08GYGbnAp+nOB2JzUWSfI2wbMsBcDvwbMzhiK7flbJKzAHgq7K9I0owJuVeSbtjRjkA1gEfxRyO6PoVkt6P6ckBcDLwZ8zhiK6fKimqNxmAJ2ZmnwHnjyjJpaR8Iem8FJ25ALYBu1IcD2yzXdJjKRpyAZwGfBz25Sn+h7D5Dlgn6Y+U4FkAwjC4D3gqxflANvdLejo1dgmAE4DDwAWpQXq0+xS4RNK/qTGzAYQquBzwLbEPibG0n/0cQdLXOYKKAAQI64H3coJ1bLsmN3nXUwxgZBCKkm8NIEC4FPig47u7nPvLJH1YGr9VBcyCmpnv5X1P33c7p3msVhK8CoBQCb7//q1ERGGfVZJ+L+y70K0agEY1+Prb9w1dtb8knVLLeXUAoRq+BNbUEtnw85WktTX9dgIgQHgbuKai2Hck1Tg1Pk5SZwACBH9ud0cFCM9I8gOZ6q1TAAHC9vD8sFS8P/frbAfaOYAA4VbguQICt0l6vqBfcpdeAAQIVwL+XDC1XSXpYKpxqV1vAAKEG4HXEsQ+KOmJBLvWJr0CCBAeAJ5cRvleSXe3zizRQe8AIt8Or0jy54S9taEA+ErOx3fzzRI/zNiYcpJbk84gAEIV+Os1bzaS2Syp+X/NPJf0NRiAAMFPbh8CHpe0tZeMFwUZGsCJwIFwlPXPigMQqmC9pENDJO8xB62AoZJuxp0AjOEuDKnhuAowMz/qXhFtNu8sBlDy9uc8AjskacN/JsGWb4DOE4gogAWDecoqprVxgycAsSEw2MJkmbvoL0K30pVTAbFqGuL6hglADxXgP4DI/tFBT+XQSwVMAKYKmIbAeOeACnON73V8mC+5EBrzHFAh/wUXE4D/XQnWRDwvvqYToXm5U13pPAaM/XdQ5CL3XAAAAABJRU5ErkJggg=="
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
    <div class="col-3">
      <div class="bs-component">
        <div class="card text-white bg-dark mb-3" style="max-width: 20rem;">
          <div class="card-header">Kampfstärke</div>

          <div class="text-center" style="margin: 10px;">
            <img
              width="30%"
              height="30%"
              src="data:image/png;base64,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"
              alt="icon" />
              <p>{gameSession.character.battleStrength.value}</p>
          </div>
            <div class="card-body" style="display: flex; flex-wrap:wrap">
              {#each currentAttribute(gameSession.character.battleStrength.value) as _, i}
                <div class="rcorners1" />
              {/each}
              {#each lostAttribute(2, gameSession.character.battleStrength.maxValue) as _, i}
                <div class="rcorners2" />
              {/each}
            </div>
        </div>
      </div>
    </div>
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
