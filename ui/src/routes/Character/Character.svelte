<script>
  // @ts-nocheck

  import { onMount, afterUpdate, tick } from "svelte";
  import { page } from "$app/stores";
  import InfoPopup from "./InfoPopup.svelte";
  import Attribute from "./Attribute.svelte";

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
    new bootstrap.Popover(c, { html: true });
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
    if (Array.isArray(keyList)) {
      if (keyList[0].id) {
        var keys = keyList.map(function (item) {
          return item["id"];
        });

        console.log(keys);

        const res = await fetch("http://" + host + ":8080/translate/", {
          method: "POST",
          body: JSON.stringify({
            keys: keys,
          }),
        });

        return await res.json();
      } else {
        const res = await fetch("http://" + host + ":8080/translate/", {
          method: "POST",
          body: JSON.stringify({
            keys: keyList,
          }),
        });
        return await res.json();
      }
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

  function percent(min, max, current) {
    return current * (100 / max);
  }

  function lostAttribute(current, max) {
    let value = max - current;
    return { length: value };
  }

  function currentAttribute(current) {
    return { length: current };
  }

  /**
   * @param {int} position
   */
  async function dropItem(position) {
    const res = await fetch("http://" + host + ":8080/item/drop/", {
      method: "POST",
      body: JSON.stringify({
        position: position,
        id: gameSession.id,
      }),
    });

    gameSession = await res.json();
  }

  /*
  
   async function useItem(item) {
    const res = await fetch("http://" + host + ":8080/item/use/", {
      method: "POST",
      body: JSON.stringify({
        id: gameSession.id,
        idItem: item,
        modifiedAttribute: gameSession.character.currentAttribute,
        modificationValue:  
      }),
    });

    gameSession = await res.json();
  }
*/
</script>

<div class="bs-docs-section">
  {#if nameList}
    <div class="row" style="margin-top: 70px;">
      <div class="col-lg-12">
        <button type="button" class="btn btn-primary">Neuer Charakter</button>
        <div class="btn-group" role="group" aria-label="Button group with nested dropdown">
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
        <input bind:value={character} />
        <button type="button" class="btn btn-primary" on:click={() => saveGameSession()}>Speicher Charakter </button>
      </div>
    </div>
  {/if}

  {#if gameSession && gameSession.character}
    <div class="row" style="margin-top: 90px;">
      <Attribute
        image="data:image/png;base64,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"
        name="Ausdauer"
        currentValue={gameSession.character.endurance.value}
        maxValue={gameSession.character.endurance.maxValue}
      />
      <Attribute
        image="data:image/png;base64,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"
        name="Kampfstärke"
        currentValue={gameSession.character.battleStrength.value}
        maxValue={gameSession.character.battleStrength.maxValue}
      />
      <Attribute
        image="data:image/png;base64,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"
        name="Gold"
        currentValue={gameSession.character.gold.value}
        maxValue={gameSession.character.gold.maxValue}
      />
      <Attribute
        image="data:image/png;base64,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"
        name="Nahrung"
        currentValue={gameSession.character.food.value}
        maxValue={gameSession.character.food.maxValue}
      />
    </div>

    <div class="row">
      <div class="col-lg-4" style="display: flex; flex-grow: 1;">
        <div class="bs-component" style="display: flex; flex-grow: 1;">
          <div class="card mb-3" style="display: flex; flex-grow: 1;">
            <div class="card-header">
              <div class="col-sm-8" style="display: flex; align-items: center; font-size: larger;">
                <img
                  src="data:image/png;base64,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"
                  alt="icon"
                />
                <span style="margin-left: 15px; font-weight: bold;">Kai Fähigkeiten</span>
              </div>
            </div>

            <ul class="list-group list-group-flush">
              {#if gameSession.character.skills}
                {#await translate(gameSession.character.skills)}
                  {#each gameSession.character.skills as skill}
                    {skill}<br />
                  {/each}
                {:then translation}
                  {#each translation.translations as t, i}
                    <li class="list-group-item d-flex justify-content-between align-items-left">
                      <span>
                        <img style="margin-right: 10px" src={t.png} alt="icon" />
                        {t.de}
                      </span>
                      <InfoPopup tooltip={t.tooltip} title={t.de} />
                    </li>
                  {/each}
                  <li class="list-group-item d-flex justify-content-between align-items-left" />
                {/await}
              {:else}
                Keine<br />
              {/if}
            </ul>
          </div>
        </div>
      </div>

      <div class="col-lg-4" style="display: flex; flex-grow: 1;">
        <div class="bs-component" style="display: flex; flex-grow: 1;">
          <div class="card mb-3" style="display: flex; flex-grow: 1;">
            <div class="card-header">
              <div class="col-sm-8" style="display: flex; align-items: center; font-size: larger;">
                <img
                  src="data:image/png;base64,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"
                  alt="icon"
                />
                <span style="margin-left: 15px; font-weight: bold;">Waffen</span>
              </div>
            </div>

            <ul class="list-group list-group-flush">
              {#if gameSession.character.weaponOne}
                {#await translate(gameSession.character.weaponOne)}
                  load weapon translation
                {:then translation}
                  <li class="list-group-item d-flex justify-content-between align-items-left">
                    <span>
                      <img style="margin-right: 10px" src={translation.translations[0].png} alt="icon" />
                      {translation.translations[0].de}
                    </span>
                    <InfoPopup tooltip={translation.translations[0].tooltip} title={translation.translations[0].de} />
                  </li>
                {/await}
              {/if}
              {#if gameSession.character.weaponTwo}
                {#await translate(gameSession.character.weaponTwo)}
                  load weapon translation
                {:then translation}
                  <li class="list-group-item d-flex justify-content-between align-items-left">
                    <span>
                      <img style="margin-right: 10px" src={translation.translations[0].png} alt="icon" />
                      {translation.translations[0].de}
                    </span>
                    <InfoPopup tooltip={translation.translations[0].tooltip} title={translation.translations[0].de} />
                  </li>
                {/await}
                <li class="list-group-item d-flex justify-content-between align-items-left" />
              {/if}
            </ul>
          </div>
        </div>
      </div>

      <div class="col-lg-4" style="display: flex; flex-grow: 1;">
        <div class="bs-component" style="display: flex; flex-grow: 1;">
          <div class="card mb-3" style="display: flex; flex-grow: 1;">
            <div class="card-header">
              <div class="col-sm-8" style="display: flex; align-items: center; font-size: larger;">
                <img
                  src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAD8AAAA/CAYAAABXXxDfAAAAAXNSR0IArs4c6QAAD/BJREFUaEPlm+lSG9cWhbs14gQwYAwotkHK4Jh4iIl/OrbRk1zxJOAnQXkS5KcgVQlRq4wjA3JkBs1S69a36+y+R0JCggt3qFClQrRa3b3OntZe++A6f+Mf92+M3blR8LVaLdNqtZxms+nwu9PpnFvrcDg8cv0TiUR25ElXOOHGwNdqtfV2u73TaDQcXoD3fT94RNf9163t93oCx7rdrsPvUCiUfvDgQe4K+C78yk2CB/h6o9HwAI/VFYwNsP/p+hYiyeeu6+aWl5fT/xfga7VastPp7GDter2+kUgkrmS1/f39jOM4/wB0OBx+e9XrDFu0G7P8dVvpJq73XwWfz+fFrVOplHcT4EZd81LgW60WMZycnJyU7FsulzdbrVZSMzpx3W63k8S2/nS73SSJzsRyclByM3EtX3FdVxaiLyEGxzjOOaFQiEQo5y0vL2+Y51mPRCLO1NTUWGF2KfDVarXbbDZzMzMzknyKxWK33W4T1/KipAF0UGIzD30u2yvI/uxvZ/tBSVHPp1Q+evRIcBweHu5EIpH1ubm5sXCNdRIXJom12+18o9HIzs/Py0oXCoUuVq9Wq3wuJU2B81C2dUzJCiyqD885g7K/grc8SN5yfX3xN9+Px+Op1dVVb39/fzsajWZ8308lEomRoXQZ8OvNZnOnXq9nFxcXN4jXbrfLYghwFgDL8zC4XjQald/GNXFTT62vrs0xx3Ek7nURXNeVvw3QdQ0hGzTeRYgpaYrFYumnT5/mCoXCZiwW23IcJz1OZbgM+Eyj0diu1WpbiUTibT6fX6ecAbhSqcgCdDqdbCwW86LRaOHWrVseDzQq6Yz63CRFSif5YsXkkAzhpmHmum56bW1NwEcikS1I0bWDr9fr2/V6XcD/9ttv647jCHjj9t7r169To8Do55rpSZCRSMS7TMb3PC/P95Q2u6678ezZs2w+n8/E4/Ft/h6HEo9t+UqlstloNLaq1erG/fv3s7///num2+1u4/bG5b2ff/5ZwO/u7mZisdgKrozFtAJgNdxa499eKBPjksUdxyEc7Jh9x8Fvv/1Wqsz+/n6eKsO9WQDXdbeePn0q3hiLxXZCodB/Fnyr1fJevnwp4H/99dedWCwm8UpC5IWbKmiSIa/+CmCXOI1rTaCRSCT38OFDqTKAV8uzAI7jBODj8fhOOBzeWlxcfDvKCy9jeVw+U6vVAsv7vi+W10z/6tUrud7u7m4AHstwjgGf1WRIQrQzvQ3ccZwMyczuBMPhcPbx48dSZfb396XEquV939/68ccfA8sT9zcCvlqtSoe1t7e32el0tmzwZNlXr17ldnd3ieM8D6qJqdPpeD/99NNYOaFQKOQJF/UYrB8OhzdWV1eJa7k2n2m3aLl9Mh6P513XzSYSCVmoi37GtvzZ2Rllbr1Wq50DT8xDcnzfz6XTaXHN3d1dybw8OAvQ7Xa9x48fC3gATExMyHPxPf3RpPfhw4c8+cECH7g8Gb3b7W4peK4dCoWyz549k/Ibi8Xy4XA4t7S0NLILHBt8pVLJQ3Rs8Lgbq2+VOur7xuvXryUx4QHRaJSHTYZCoeQ333wj4P/880+sQyIMXJuar58Xi0U+J1nmms3mL6lUSkom4EKhEPEuiY4X4YGl19bWBDyWD4VC1wt+kOVt8FjfWMGbmJjIvnz5cmjCOTg4EMsqAJMPvO+//14Wp1gsJvsZGpncdd0dwGqJMx5FSGSfP39+s+ARJzTm8/l8EPNYHvC4Ij9k8omJCS8ej7998eLFOQlKwduVgNL28OHDnpzAInC9Vqu13W63IVWywBoO/G0odGD5iYkJLC8s9NpivlKpkNkzlUpFYl7BY4Wzs7OA3iodJZvzisVi/PZisVjuyZMn8kDFYnETWks5A4yRtyA64i0HBweECmDX7QXiXF6AVkkM8NFoVLM9ueRGwAvJaTQaQh1xQ9/3heFR6rC+xiALwEPhAZQ2XvF43Hvx4sVY2Z6c4Pt+0CpryVPQdsvMPQAPyXn//v16NBrdIftfa6mr1WqbUFult4Dvdrs7WALwWuv7tTprEWBs6ZmZGfFGzfaDXDMUChHbAl5famkFrgQJ8LFYTMogslckEoHeXjt4bWykhpJZXdelHovFKVlqoX6VlgdVVqfdHt4wrMcHoN259S+oDdwsbvq7777LFYvFTCgUEm6/sLAwUu4eu9QhRTcajZ1arZZLJBJpA36brG34eyBP2/22WlaprXqCqjC2aKE9vt2+2teyWmIu65kF9brd7gYc4eDgAOCInjcG3kskEudi1+7SWBAD+o2CNxxdmhqzEEHfbqit3cicex8OhwuwREAP6wCpIvCHTqdz7S0tXVS+Xq978PuLHmJUibnuz01JhADtGE+5diUnWa/X80pMiG9jTRQaUWqMBaX9VLWm3W7L8cv061rfrUXCSwC3wjHKoLmH/La9y3x+PeDPzs4yqLXlcpkHgGyQ4KRHt2uteZh+1VX+7hc08Zp+FdeWr/Radr4Y4S268PJ7YWFBeP3p6en6RUruhQnv7OxMmNXMzMzA+mxbiMVQAMhN3Bw+rw+tC2Wdcw6PChrmu/q5Chzyt+u6BZok3qMAzc7ODhUqz87OpMP78ssvB1LtoeCr1SoEhmFjoNZed6ze9PVOTk4gPIRGbmpq6lyXNxA8Ki3Am82mNzc3F1i9VCp1ybiGW3vJZFL1e+hqATDEuE5U8QzIjN22XgWwNjnlchkghNsKnnT37l2x6KdPn7bxMt/3YZ0cF1yEajgcZgFQmr3p6ekeDx4InqwOwMnJyZ6Tj46ORKc3Awrv0aNH8jmyknJ1FieVSsnxjx8/0lvLBIeHGjSi7o99m7oaN/eWlpbken/99Zd0g0a2DkpuqVSS+1ucP0h4tOGtVmuH748FHsujhdtWYhVbrVag0+MVT548kYdCTTX1FaJjt6b5SCQiq+77PmQkxwIY+Uo8xfp5YyzEIcnug8Ar52eR79+/L/e36ruOwnvm+Wbgst3v+hcmPACTyO7evZs7OjqSzQZYHR4P+OfPn8vN//jjDwFv6K23urqqffn6OPr5oFCwk6m6PZa3OL+3srISeJiKI6ZLTCOAlEqlDKrOsKQ4FDyrhU7f6XR+mZ+fz8KbESwVPBZeW1uTm+/t7fWA/+GHH4ZWBxaTByQc+C6uCk017E0caRgnUPBwDXKLgjfKj7i9Df7o6AgpDbqbHrQAA8GbOKF/h8+LWkvH1Ol0trWF5eYqSA4D//79e26O2/Ng0psPakvV8hbfFx6AHBWNRt9xf5PYbGFzKHgam+Xl5ezh4SGWh+9zvXMLMAy8ipUopDQNqKZkdBEOsT6WUsur25uV70mEZOF+6UknuQq6f1Kri2C0AO/rr78WTzo8PJTEZkQNb3l5OYh5TXhG2tpaWVl5C3jT5YmAOj8/Pzrbq1iJNIV4QbuIVA14W4rWmEdqtmIukKNQYTUXqMau1rezut3a9nd/EBnV9gCv9yGB3rt3L1gUTbgK/sGDB2+LxaKIGywc5y8sLIwGDzPC9VFnWq1WD3gdJrCSmu0VpJGlqP9BIsIi6i2qs6vwaLK5OIBtfW17jUoTLOanT596SqpdAm3LI6zeu3fv6uCr1aqAr9frMvve29uTXtm4NjFIVg80N115jjPIBBAJx8R7hlCxrW/XfM61RtS2BJZFCFVtj50gyie4z507d+Q+x8fHcrzT6cj0lmkxgsu/ZXncvlqtCvh8Pr/NGAmLawwOKlGDjuGu9mBR9+QN28FhLB/kjnHv8/nzZ0mIUHLUW8olQwy+f+mYN9IUhM0rFAoCnkSn4Ae0nj3PqfUZd+WhVG8ftBvTVml4z2AylUoJ0TId5dA10DIGeEgQ4BcWFgQ8QwzzxR6qLqE26IrVahUmJyPgpaUlOefDhw9IVrhVENPEutZrmgdjSe2xA1qqZMn3/Tem5En5g/EZ0HxHdAE2HDqO887ecQkoZX98xyRL7enp7CTHKHjGZvPz82mzaDL94fr93emwUie7rLCU7r/5+PHjDo0D5EbJBeBN2xrwbCUcuNkguWtcF7bPA5SJ9cB6EB5DgYNj5XJZOb6AN/mAXCXiytTUVE9rO7aAeXR0hEwt4LW+WpYPrKy1GPCLi4tiEbou09y8I2yGUV4TRlgUPQBqLAAUPNe0razg1aKcpx41Nzd3tUElF9DtZRpPpVJJwMPpLfDC4JCwtL0kIxN3djYm5rUhMclOpS3tATSZ2t1fcB+uqR52+/Ztsd7p6emmtqpq0ePjY7kPA061PCVbvYh9QrZHDevnZecDZWN6elrOKZVK8Hy0++ChxnVhwGsYWSQHhWbD7NgCvLS+2v6yoNoaj3ufk5MTSayUOg3X09PTru4I++KLL0aTnEajsQl4avr09LTM1wDfbrcFvO/74lLagJBY1EP633MeYy1NoDpkVGJjb0+xt50BPhwOpxFDtGpgRbVe/3uuRyuupY5sz7GTk5NtsyfQu3Xr1tVi3gKvW8AC2kk+iEQieuHN2dlZTTaB+KDbU2x6a29M6hc1lOUx5FQae3p6ClUN7qP9OYyUUNUhJpa/8pRW44S4V4seHh6K5XU0BVfW1hXFJhqNSmzZZOL4+FjKo1Jitbrd2AwbWZlaT7srEyKuDUgrPAJZiuPG3XWKG2xLwRN1LjhWzBt9Xup8u90WSahYLG77vt8DXreZMHJmUmrU1x7BUwkKm5ZbrdYbjWlLqZGQtjYcS1Lyfb+n1nOM7XA0V2bxshqSgNcwNf28gDcantR5rt8vyw2r80xmBDyNDYSD3lxbWvOfE0FjY3JCBsla+fa4Seqy5zFH4D62HK3grdm9bJREfWJfnkrit2/fHp3wlOGZcieSkILXlhamp5YfBuDz58/STnKuiXWZ5hAaHNOEadFkQkfme5QsyhvlTLu3YfcZYHnZhIjai3qr9xwLfK1WC+htq9UKlBzXdbcBoTKSxjxuzyARi+CSujFA1VZ7d4U1vAhG1P39vD2DJ+trwqN7Y2ih99GaT4kz9V03I8uUtlwui5JjFm1s6Rp6u2VAyoVoDxkEWvvfgq6LnRSWRO199dVX4l5IyqbRCDYRaS3vb2S09Kl1rWmuTXaETptrBGwPcqP/1GB2YqcRXU9OTgIlh5Dtn9xcJGDSxKy02+13s7OzOZKH7/vB5j/c1t46pnIVVUAlZaiuliDt6Ox/Rui3uP6tra7ZbBT0EniSGU5I6Ny5c0cWGfAqZpgtMSmqFLO6SCRCGP3Sn+llscdNOEbUlD1wmvBskMSm2U3RY3kFb5c528r9+p39N+/D4XDg9trdmQUMGhrcXu+vI+qLZniBd40LnvNqtRq1XgVExkLab/fIS6qVaaNhSco9U5tBi6DHdBGIeb2eXefxME1gzBW166OnmJycHLkN7VKWv2iRhjUPuJ1+D48xmb/nUnD7i36QsSxKK30/5w9y48sY8trAX/am/yvn/xPqIREhUow9dAAAAABJRU5ErkJggg=="
                  alt="icon"
                />
                <span style="margin-left: 15px; font-weight: bold;">Rucksack</span>
              </div>
            </div>

            <ul class="list-group list-group-flush">
              {#if gameSession.character.items}
                {#await translate(gameSession.character.items)}
                  {#each gameSession.character.items as item}
                    {item}<br />
                  {/each}
                {:then translation}
                  {#each translation.translations as t, i}
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                      <span>
                        <img style="margin-right: 10px" src={t.png} alt="icon" />
                        {t.de}
                      </span>

                      <div>
                        <button on:click={() => dropItem(i)} type="button" class="btn btn-dark btn-sm">
                          <img
                            width="16px"
                            height="16px"
                            src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAAXNSR0IArs4c6QAAAPxJREFUSEvtlTFqAlEQhr+/iDewS8BCEGxzgVwhkHiVlGproVUOESRNLpAcwC4kWOUqvzx5gjxc5y1sup1u2Xn/N/O/3RnRImzfAHeS/mqPqTbR9hp4BEbAO/AmaRudrwLYvgd2hdhW0qwrwAKYl2KSwgLDhCRqewLsC8BK0ksnHWTIK/AMDIEvYCPpozNAhgyAiaTvSPj0vsqiU7LtMXCbniWlLsKoBtieAj9ZcSkpXXwYbQAPwGcPaPTUdm/R9S+utyj8I//NItsXd0GuKI2OmaTfpgqrRkUDJBQ/DsXQm5xQQKrEWwHyPkh2PUW2nBdd3cHZTphe87x05ADOzHgZjdoCRwAAAABJRU5ErkJggg=="
                            alt="icon"
                          />
                        </button>

                        <InfoPopup tooltip={t.tooltip} title={t.de} />
                      </div>
                    </li>
                  {/each}
                  <li class="list-group-item d-flex justify-content-between align-items-left" />
                {:catch someError}
                  {#each gameSession.character.items as item, i}
                    {i}. {item}<br />
                  {/each}
                {/await}
              {:else}
                Keine<br />
              {/if}
            </ul>
          </div>
        </div>
      </div>
    </div>
  {/if}
</div>
