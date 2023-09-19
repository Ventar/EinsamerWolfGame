<script>
  // @ts-nocheck

  import { onMount, afterUpdate, tick } from "svelte";
  import { page } from "$app/stores";
  import InfoPopup from "./InfoPopup.svelte";

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

  function percent(min, max, current) {
    return current * (100 / max);
  }
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
              aria-expanded="false" />
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
                  aria-valuemax="5" />
              </div></td>
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
                  aria-valuemax="5" />
              </div></td>
            <td>{gameSession.character.battleStrength.value}</td>
          </tr>
          <tr class="table-active">
            <th scope="row">Gold</th>
            <td>
              <div class="progress">
                <div
                  class="progress-bar bg-success"
                  role="progressbar"
                  style="width: {percent(0, gameSession.character.gold.maxValue, gameSession.character.gold.value)}%;"
                  aria-valuenow={gameSession.character.food.value}
                  aria-valuemin="0"
                  aria-valuemax="5" />
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
                  style="width: {percent(0, gameSession.character.food.maxValue, gameSession.character.food.value)}%;"
                  aria-valuenow={gameSession.character.food.value}
                  aria-valuemin="0"
                  aria-valuemax="5" />
              </div></td>
            <td>{gameSession.character.food.value}</td>
          </tr>
        </tbody>
      </table>
    </div>

    <div class="row" style="margin-top: 90px;">
      <div class="col-lg-4">
        <div class="bs-component">
          <div class="card mb-3">
            <h5 class="card-header">Kai-Fähigkeiten</h5>
            
            <div class="text-center" style="margin: 10px;">
              <img
                width="50%"
                height="50%"
                src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAH8AAAB/CAYAAADGvR0TAAAAAXNSR0IArs4c6QAAC2FJREFUeF7tnQnMXkUVht/XiFEipAbUSGrAiohBQY1LFW1EoAm4sCgCUXEjgKBVhFiXKiiIUhesprK5gBhtXUBRcUEBg0gEhQiiEkFBgwuKcYkKanzNKfe3///1W2bmznL/e+ckTQM958zMeeZ+d5mZc4gqg40ABzvyOnBU+AOeBBV+hT/gCAx46MWufEmPB7AEwN8A/IbkbwfMocjQs8KXtDeAVQBWNOBHB30GgC+QvKpINAbWaDb4ks4FcKRjfNcDOJ7kvx31B6UmaSsAOzeDviU0TlngS/oRgN09CV0G4LUkf+Jp11t1SW8EcCiAxwK4XzPQfwH4MYCNJNf6DD45fEny6dCI7k0AXlQnACDpuwD2nBHLq0g+wzXeSeFLugTAfq6dmaA3+Akg6ZcAdnKM420kH+Gimwy+pI8BeKVLJxx0NpA83EGvdyrNT/3pngNb7XILSAJf0vMBfMmzw7PUDye5YZZSn/5d0r4Avhk4ppUkL51mmwq+y/3Jd0zXknyKr1EqfUnPAnBSTP8k95rvT9LrAdjrb4jY29IHs8JPdNXPjeFAkrF/UUICaw9gBv/yIOPxRlvcqyWdBeDowDbOJnlMbvgx7/Wjfe/MvT8B/CvGXPk2uWyShcgW/kadRP3Zl7QtgFsBbB/SW0ebR5G8xVE3mVoC+HeQXDrys7+u+SIaMo4PkXxdtitf0j4Apj5khIxixGYVyQ9H8NPKxQT4V7RxOubKfzWAjwT6PJbkmTnhvxXAqYGddTX7Gsn9XZVT6U2AvxfJVhNg5MpvczHtS/JbOeFfDOB5qQI+55dk1NtVSH9zwLd+BX4v+TjJV80aV9QgSroOwBNmNRrh37cj+acIfoJd5ILfTIA/eDxH/ZHkg10GFhv+rwA83KXhljrFH/pywm8mwCcAvHxG3M4j+QrX2MaG/3cAW7s23kJvOcnvt7BvbZobfjMBbEn8OAC7AbBlXRNb9rb1j/UkP+ozsNjw/wHgAT4dCNTdg+QNgbZRzErAn+t4s57/6Oa/b+7Eer6kOwDsECW6051sTfKfGdqZ2ERJ+LHGHfvKt00F9pOUUrb4GDLamKSYn12njWX061vUV72UQTTfseFfCcB5M0Hg4GZ+tmy5gSSwW5vMBg3/5NgrXWNIvIvkmmmEKny3+Rv7yl8O4Gq3poO1DiZ5UYUfHL//G0aFb14l/RTAru27NtHDziRt8Wjaw1i95zsASAH/PQBWO7QdonIZSdv7X1zq0/4YBM1JnOsT0TmKpO3/Ly4V/gQEkj4H4IWRCd0O4HEk7XhXcanwJ8O3/eW2rh/za18n1vHnhlzhT3/osjN5thMlhnya5ItjOIrlo8KfEUlJ5wM4omXA7cneNibYwYXOSIU/G74dwbZ38tBNiNbCzPf6EjOiwneIevP0vxHALg7qoyrvIGlfDTsnFb4jEkkHAvgkgG0cTUzN3hjeC8D2s9tOlk5JhT8GhyTbtm0nT+yn3rZ0Pa0lNdsgYvBvA/AVAN8jmfoT8swuV/jzQiTpuQCOyrGBE4Bt5LjA/pD8/UxSCRQq/Caoki4EcFCCGM9yaXsG15C0iZBVKvx7F3Ls9Mwjs0Z+y8ayPxgOHr6kOwE4bRPOMDlWkLTNJFlk0PAlfRbAIVki7dbIzQD2Jmn7CJPLYOFLOta2CiePsH8Da0mmWk5e0JtBwpdk+/KvybBR0x89cDcA29Nv2b+SylDhnwDgfUkj28650zm1dk1setAdl5yhvxs4JW3XXPXL2gYvsf0+JL+dso0hwn8bgHemDGok3xeTPCCSr7FuBgVf0n0B/KwD7/QuTP8LYBlJ2/2TRIYG/2BLipwkkmmcHkPy7DSuB3bPl3QegJelCmYCvxeRtAmbRAZz5Ut6SPOT/6AkkUzj1FYDtyVpt4DoMiT4luLD6+x39GiHOTyApKWKiS5Dgm+JDy2l6mKTc0iGJjGcOtZBwG9+8ousmUeYaTeS9M3z79TsUODb8aipKb2colVG6R6S90/R9FDgt0n+myLuvj53SvG+PxT4KXPp+oIM0Z+ZjDDE6VDgW9arzqQ6DwB1HMnQFKYTmxsK/FwZtgK4OpmsI2m3rqjSe/iSHgNgsVe3SpKrdwjwLY9uko8kUS/D6c5uImmlyKJKSfhZ6upJapPyO2qwWzj7M8non6VLwM9aV0+SpU+3NOqLXR5I0r71R5Pc8LPX1ZPkkuw3WkATOtqVpO3ujSY54RepqyfJsmtYwv/FLral28qzRpNc8IvV1ZNkT/r2xL/Y5YjYR7pywC9aV0/SX2xNfLGTB/AWku+eNI4WuXpHk060KrHSmbp6zeuEVWjug5xK0jafjpWC6Vrn96c7dfWaMml25fdBTif5po7D705dPUkPBfC7PpAH8H6SJ3Ycfnfq6kmykt2dyoDVYiJOLTDY4Xt+m49s4XX1evJdf26+nEnSDpdGk0xP+2Xq6kl6IoAfRotWWUfnkrSUMdEkB3zrbJG6epIshaqVQu+DeJUccxlwLvjNBMhbVy9TXVyXOMfQ+RTJl8ZwNOcjJ/xmArh8avea5BPz7Uvqw3LuHKuNJA9bzPCbCZCnrp6kQwFsiBmwgr4uJPmCmO3nvvLn9z15XT1JVrrTfmr6INGPbJeEHwvItJ/9Nu+YsfoXy88lJJ8Ty1nzE9zfzByS3mBfxmIGrKCvS0mujNl+369828FjO3n6IJeTfHbMgfQavk+gAjcc+DQxSfeZJIt8i6jwGySS7BWkRNWr3UneGGMW+fqo8DfDL5WyZUeSlnw5u1T4m+GPe/JNDUQAlpD8a+qGxvmv8DfDtzPwybNejkBIsh/fdSJV+JvhLwXwa9fARdK7naTtOSgiFf5m+JaP9y4ASRIhTKB7A8k9ipC/d6m1vx95fIMqyVKd7+Br10L/SpIrWti3Mq3w54VPkr1yRT8QOYXQl0kWSxJV4S+E/x0AOa/E6Gv0Pj8FFf5C+FY50+rn5ZL1JF+Tq7HRdir8hfBz5+45jWSxE8QV/kL4Vv1y4t74BFfoapJrE/h1clnhL4T/ZgCnOUUujtLRJM+J48rfS4W/EL6lOT3LP4zBFoeRtALNRaTCXwjfyqxZubVcsh/Jr+dqrD7wTYm0pNxpWp9esqByvfIXXvlWMfu6jFfibiTHpolrcfbOt/uj5/P7W11rWmQk7diUOfcNYKj+0knVMwuetx8s/G2axZ2tQml62P2nWcsfm2Gr7/Cz5OHzgLFJVZKd57dz/anlLpLbT2qkr/Cz5uHzJZgxgdMvSE4s297He372PHwB8G0nrZ3uTS3Xk7Qj5MUk59N+kTx8vpGVZHl67YBnaom+D9+3w7ngB26Ld/r0PfG4lm8wmnt+rtp7XyR5UEgfY9nkgF80D59voCR9AMDxvnYB+ueTtIOkxWQC/H7k4QuJqqQ1AE4JsfW0mZpgydNXkPoE+EG+GqPu5OELGYUkS3q0PsTW0+YUkm/3tImqngB+d/LwhURKkmW/+EyIrafNiSSLniBOAL87efg8YWxSl2THoL8RYutpcyRJ2zlUTBr4J8XswJjcu21yJITn4QsZlKQnAbg2xNbT5hCSn/e0WXTqLZNizSwpF/tVbxmAWzNEeSVJqwXQeymShy8kqpKWNIs79wmx97B5KslrPPQXtaqkvHn4QqPl2dHQZqKXTQntSC47x5I3cfLwhQ5KktWy2SXU3tHuYST7khHcccibHqjz5OFz7tGIoqSrASwPtXewu6dZy7/bQbeXKsnz8IVGTdJXAewfau9gdyfJHHsGHLqyuFWiPu1bKCRdAOAlCcPyc5KpbysJu98d1yngrwOwKuEQf0DyyQn9D8Z1CvhzX73Mt+XNmfvbgjrX3vz/790HkicPhlDCgXoHPmFfquvMEajwMwe8S81V+F2ikbkv/wOYTG/LhM69cAAAAABJRU5ErkJggg=="
                alt="icon" />
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
                {/await}
              {:else}
                Keine<br />
              {/if}
            </ul>
          </div>
        </div>
      </div>

      <div class="col-lg-4">
        <div class="bs-component">
          <div class="card mb-3">
            <h5 class="card-header">Waffen</h5>

            <div class="text-center" style="margin: 10px;">
              <img
                width="50%"
                height="50%"
                src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAH8AAAB/CAYAAADGvR0TAAAAAXNSR0IArs4c6QAAE6lJREFUeF7tXXn8velYvq7UWMoWMkSIsRaRkoiUrSJJqElqGvuM7Esha/YlWxkx9LEWDZkolUkiWyTJGrIvZStLKl19rl/nfOec9/ss9/2+7/nOfM+c+5/fcp71vp7neZ/nXokdnWU5wLPszHcTxw78s/Ai2IG/A388ByQ9JFBbwN4p47//NcnXBuptrIikqwO4GIALALgwgKMA/BuA00i+e2MdBxru8HSVl3utkYzgsNb75J0v6a8A/GhgTqtFrndGgS/pRgBuC+DYxpj/CcBLAZxE8lPJuU0uLskAp4hkGst0heGIDgv4ko4G8AgAxye4+kkAvzdmVyX62Fd0B/4U7g3qSvqZBfBXGtnsa0leb2TddLUd+GmWlStIuiWAP5yjuTFH65h+DxP4w++9//3gwaT37ZqD+ObPCfxiPgdyAkga8tT8XP0/X5YfusrjMfyc/M0v3AF861wD/6B2zOpYJH2PXxUAvn3M7mvUeQnJW83cZrO5wr1qlkW4zeC/CMDPbwikE0j+zobaLl0Ahy+qHfg15ks6DsDJGwTHr4DrkPzgBvvYa3q38xNclvRGAD+UqDKm6NNJnjimYrbODvwgxyT5qPeRv2my8OeKJL+46Y524Ac5LOlPAdw4WHxqsduRfPbURnr1d+D3OARA0mUBvC9QdK4iryL5U3M11rjD7C58PSZL+gUAL+yVm/H3fwdwQZL/PWObu9v+GGZKejyAe42pO6HOtUm+YUL9btXdsd9l0ZFjf4yGMdBys8i9SD5xaiOt+jvwA9yV9PcAvi9QdM4iTyB57zkbHLa1Az/AXUkfBnDJWtExYuaFYcVQV7HaxckkM2riwEzWi+zAD7BM0hcAnO+AwX8ZyZ8NDG90kR34Adb1VKEb2vmzyNl33/wAwB0mfR7A+Q945z+XpHUJG6Pdzg+wVpIVLd/dKNq1Kyjo0kv2CatdPHTTZl478GPg/x2A7w8UXRbZB1zv01Fo+ziSz030mS66Az/AMkl/BCBz+ZoD/I1bIu/Aj4F/O1vbBorOufPPTfLLiT7TRQ8c/MD7Nj2JbIXs7VzSFQHY5j5KU3f+W0heI9rZGSSBXB3e2sukasZ1GMH3LCW9C0DURHsq+M8geecd+FEOJMpld/4C/IxyZyr4Nyf58uiUdjs/yik7941wQZL0wwCiWrYp4KeFO4cd/JpzZcZXr+WgudbOGPAXu/81AH4ssM6mgJ9+4lXAP0iejv/ml8CQ9E0AvhFg9LLII0k+YFi+xJgJ4P8ygMjbeyz4ryN53cScjxSNzlHSjwP4y0T7UZ7ODv63Asg8dYoq0ChjogyRdBKAO3TKjwX/+iR9uqQoOkdJNg37k0TjUZ7ODr792+3XHqWiyXOUMdFOFjvtHwHYc6dGY8Av7rLIuKJzlGRBlQVWUYrydHbwHeDgY9FRAngWydtv6tiXdEMAVwbgP28w8873Ij8FwKkA3pb13U+An7VFjPJ0dvCPAfD+BPjPI+ngCGsUZUytH0lu09/6yEVv2cyYnb86hGcCeCbJt0XmH53jCI+j55P8pQBPZwf/ewG8MzL5RZmio2OUMYUJ/jSA+wK4VmIMc4G/bOdpAB5LsnkCRuco6U4AfjcxnyhPZwf/BwG8OTHQV5C8WWCVNt/5ks4JwEz/1UTfw6JTd/5qewbeC8BjKlIC/LsDeFJiXlGepsBf67+kt5bkJ08muNKfk3RcnOGxv7ST2xM51/Tkkr4TwO8D8JNoCs0J/nIcTyZp8PaRpNAcJd0PwKMTExvF08ku2osAR3+WGKgjcWWEQsNFcikAFqn6UjeVNgG+x/Rokr8+dnCSfnMYfKHT1iiezgG+4928LDHRN5G8ZqL8EPwXdCJpZZreFPgew61IviQzmGVZSY8EkFk8byaZ9kqeA/zss+QdJK86kil3BfCUMXUrdTYJ/ugFIOkJAO6ZmOcons4BfjYQwntIWu+eog2FWdk0+J9xnACS/5KZrCRfGk9I1BnF0znAvwuApycG+iGSl06UP1JUUivMylpwokzbw0tlMKJorYuSc8cTSab8ByU9Kxkv8MMkW4arxfHOAb4nZh16lD5J0rf1MPWEHmMVQOEBBAo2jF/swevd//ZAM8uF/jwAt4mWB5DmqdueA3xr6BzZMkqfI3nBaOHFrreGq/qsO5OD7yk8jaTvKyGS5LiBjh8Ypc+TtI4lRXOAb+D3qWgbo/gyyXNHRympK0Q6BOD/B4CrRgM4SXoFgJtGeQTgKyS/LVH+SNE5wM/eTP+H5LdEBxp59hwC8D3dh5P0+71Lkl69UEx1yy4KpHi6bHQO8H3Z86UvTBmwJL0HwOVbjQ/bW3jdFD1rMzF0JV3boJX6HrYTMHj9qN3HSdqZtEmSLDFNGYtkeDon+I53l/VVOyoSykTSzRcq1CazKuA7UMOQXk0yHKxJkr1/7AU0pH32ewHw3cY9SP52APw3AQibhC/aC/F0te85dv6YSJfnIenvYG8HWH6/T/07rJQAv6gAqQ1Ckk3AbQo+F/ghYczIIBNp55E5wLdo1yLeDF2IZNf6R9KHAFiWP9fOT8XNleS3cynKZmnn+zMTyXZx9Z7+P/KpKzAkxNO5d76VOvu0dB28LkbyE60ymbBqiZ1fNHpo7PyLAiiNcwr4dyRpI5AqRRf9oIEuT/edmL1d1ftdkiNbX6dXbvD7ZXrPnkxYtQT4RXOnBviO2P25GY99N2XLnzt2wPeC88LL0KVJ+qQM0xzHvg05/BbP0JV6SYwyYdUS4Kfi5S4MRr4aBD967Nv2z8mdWjvfCy4bKr7L003sfJtw2ZQrQ1cj6chZLQZEHS/2Wfwsnnql235Kzi7Jm+N/g+DvyzPQmN65SH6tceLYFN4m8Rnq8nQT4H8AwGUyowRwTZJ+zrTAbwZXGlQsKXZK7/xHkfyNzFgrwRr8DvfnbkitqF2rZa9L8nUN8K0P+ObMOCM83QT4tl2z+XaGmgENMpe9TKe+jZNMaQAleYeeI9lPr3gzcOOI6CDuLx0kYo5vvp9sWaXCjUlahFkkSRbEOHr23HR/ko/JNNoL75Zpa6XsU0n+WqmupLMD+M8R7TZ5WmpvDvC/AuBcycHejKSVFzXwNxUzPyRhWx2UJGfVuEhyfr3iLyVZ1NpJOi+AMTH8mzzdFPh20rSzZoZuTbKa7kySAx5sIofNXUhm7OFtRBISNGUmD+D1JH+ksvO/A4AtgLLU5Ons4Dduw72B35akDRZqO9/GizZinJuOJ5nKvSPJYV7SZmedgX+QZPGSLOniAKwEylKTp5sA38e9j/0s3Z6kTZVq4D8WwH0qP/vCNvQT8LMwcvrchqStf8Mkya5YVwtUKGXcLD033dRXSRafcpK8KPyCytIdSGaCUU3T50uqScB6Az+RZNXuT5LFn/ucOReNlowu/wtAxEbgliSdIDlMkhzlw9E+euRsW3+zLNSQNSyLnJekkzWs0cJQ1d7FWbpry1uo1NikC9/Cc+bj2VECuDdJG4EUSZLt3X+u8nMJfN+OfUvu0U1JZvze/c1vmpCtdHgtkn+bAP/yJPelhGmokXtza/J0E+DbCvefe6Mq/P4AktVvuqS/AHD9BPjRF8cNSbrtMEnyYonk0bkGybckwC++y5MxhVbn8UCSvxWe2FQzrglH1MNIVqVhHTOm0s63bUDEhq0pWSsxrnMKrVZZU9UGjv2ilFOSXczTUT8yZmLLQU899n8AwN5qT6w6e7PaGbF27P8xALtel6gEvt/Ffh/3yCbUGY9iH/tRM2obaL4jsfPXyq/U+wkAr+pNpPB7k6ebOPb9Vq3KqBsTqHqyuo6kP7CvWwL8Zqj1lXaKDG8xWpJv0A7r2qMrk9y7qAV2/hVIvnfYqKSs7+OyiaeQvFtvkKu/T935Dn1SFdM2BtKMXCmpZb5V2vn/6hRngYk7A6YNQsMkyb6BEZv7tbYD4F+q5MYl6dYAXhwe4OkFTyLpoA5hmgq+j2Yf0Vl6DslqUIVOJK0S+J8GcOHAINIGD5JaMofVLi9Hci88TQD8i5D0uNdIUjSM3LBqOunDVPDHrtIXkTy2BpakJwMoKj7st17wr4vK39OmTpIsVIrY269ZJwXAP38pD68kh49zGLksvZikPabDNBX8XwHwnHBvpxc8heQtGuBb8+Y4OyUqgR9VK6eNHCVFRc2XJPmR5YAD4J+T5D7tnaSxbujpRE9Twc8GDlry5pUkb9IAv7XbSuCb6d8VWIQhk/HVdiTdA0AkaeLFSe4JvHrg15wsJFms7U9NltJ5faeCH2XMcCKvIVkT4vi23/L5L4Ef1bydnaRFwWFKRMa66Gpcvg747yV5hdIgJD2w5iXUGfRpJFMxiqaCHz0Sh+OuqjRdUJJDjLwxcexbytj1+R/j0iQp+mm7MMnPBo/9l5O0N9I+kmT3MC+ALL2BpN3LwjQV/IcBeFC4t9MLvpVk1eJX0tEAPpUA3zJyp09v0TdIZu3ivBCjhiUXIGl5wxHq7PxqwKbE62I4165V8LDCVPAfZyXNCPDfSfIqrXqS/P0sBXEoHfvvBlA8Rlf6qKpRO+OICl3OR/JLQfCrKu2EXGE47HeRTFlRTwX/qQBOHAH++0lersP0mnKnBH4ktcoXSGZt4b2Do/aEa75ynZ1/A5LFkOrBaOEl1n2AZO/0W6s3Ffxs7Jhl5x8hWU14vDg2bW5VkliVwP+HQFy+T5NM2+IlgkyuPd064F+WZNFgQ5LzBFjQk6WPkYy8ePbanQr+8wH8YnaUALpASLo/gEcV2i6BH0md3l1wpXlIsqt008dgUW/NRboBvqV6l6i9OiRZtGvhWZY+SzIi5ZwN/GwSw2XHXyRZzXm72Pm1i1YJ/EgmzfeRbAZ5qIDvu8metq6ByNlI7nn3NMBvBqGUNMbr2cP6EslqBvHSuKfufKserYLM0tdIZs29q31IivgLdi+ZFfB9N9mnfRuUHXWZrPQ3lqdfJ5lyLpkK/mn2FMkib/83kmcbUa9YRZJlAr3wo83nZW0skvwd3RPbVsp9lOQl5piPpLCP4rC/rBxjKviOFVc1wW4wIxWarMdUSa8PxNtvCpYa4Efs6N9OMpPAuXWK2XZxTMzeJ5HMhGydZr27+Da3rG5qkzwHya/3QI3+HowR0BQpN8A/j7+nnbEUQ55Hxz8sJ8lWwBlpnWUiNgtLGdNO2vkL8B0+vWafXpr/fUhmInZ2edgx+FzWbyqTGuAfBaC3UF9AMhMxszknSfbff2t34qcXuCfJTHKGIzUng79YANFwbB8naY+UWSkYsbKpRm4NKOA12zRLGzPZhO3gG0lG/Ar2DWMu8A2oPVsu1Jno6Bj0HXAiOfSaBiSd9ntOIQ8imQlB210PkqJZy9KOKMvOZwF/sftrQpllX6MuXF0u/b8SxSlJqtbAizbSZk7LviX5m+9vf43uTPIZkbFmygQ0fKnoYsO+ZwN/sQBafm1r7kwZJvTKBnPSpA0cV8C316xv/TUavfs6J46fwzZLr/kkpK2RV/ubG3z7nJdcr1Mh0HpgD38P2r2lTZtXwHeyhNY7Ph0VIzpHSbV8Bo8nWXNmDTU/K/iL3e/kR8PUaceQHOPWFZqEpNqiW63/OJI1u8BmP5Is4WtpIa9CMpNbMDSvlcU3TAvrJ53t/jM5jPf1uQnwnT9nNbHA5BXa45Qkm4T1fPAeQXKM4YnvFJbtt+wP0lbBvTmt/l6IQTzLHWN28Be73+94Z+Dwyjya5Bgf/jB/gu/i0TfygPi4GVotPJFGQUnLSKezXZw3Bb6TKVhfbf+xiOXrJP4EAxrcl6Qtj9JUyoS50sgoI5HsICQ5HZ1dwKuGINk2NwL+Yvf7aErFv8kOfuWbaPnCnvFkpZ27kRyVlk2SI4PVQrWnLWgmzNOeTtnw9tXuNgb+2AmOqSfJUTl6Jtl3IjnGE8bf/JaOfbSELTtXSXYMSaVpa/WxFeAvTpqep+5xJG0ilSZJLwRQc4U6lWTNnTzd10FW2CbwHRe/lVvuWJJODJEmSa1sIk2n03RnB1hhm8DvmXLdguQpY3gryTEBHRuwRKPlB2PGMmedbQK/FcfHPLsJyVeOYZ4kv1jsmlai+5Ec41s3Ziiz1tkm8HuJCEc/kTrp3ZoxBWdFa+bGtgn8nlr3BJKjQrpKejaAWjCJ0Z+TmbFMN7dN4PfUur4QOiBTN7HTKhcl2ZX81AZnN6bUSaOZrLBN4Ec8hqsOkjW+BRIcblSpk8QzVXybwI9E6rYtnnd/xAnDwh1Ht+olQdyoUieFZrLwNoHvGD+RoMoh24KFm7iNKFtZRBxu3g6a1Xw5STwOtPg2gf+TAKJPue6zL+gq/RmSjiVwKGmbwL+WkxgEUdiXFHFwyYu2lfaJD47vQIptE/i1vLc1RlYzW0ryCeKTpEfNRdSrfEb/vk3gR02dlzz3089+8mt585JBEEf7ApzRwLv/bQLfFq7dzNwDpj+c5F6ARUnO1uEQL82oISttpNKznhkAXx3D1oDvSUnqqXWH/PfTz7v/SE4bSdkAU+lI12emBbBt4PfUuiXen0zyeEnHLHZ9JmLXoVXqbNWxv9i50WRIw0VgXzcHlqrGA67s2EOr1NlG8McGi3DY+BuNOJIPrVJnG8G3sUYxsuUIYCNVDq1SZxvBdwRwh0s9KDq0Sp1tBN8BCu5+UMhb7k/yEwfY36xdbdtt/yF+sS3kF8M/Z2XcorHHHFalztbt/E2gu81tbtXO32agNjG3Hfib4OohafP/AKJbjQc64ytXAAAAAElFTkSuQmCC"
                alt="icon" />
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
              {/if}
            </ul>
          </div>
        </div>
      </div>

      <div class="col-lg-4">
        <div class="bs-component">
          <div class="card mb-3">
            <h5 class="card-header">Gegenstände</h5>
            <!-- div class="card-body">
          <h5 class="card-title">Special title treatment</h5>
          <h6 class="card-subtitle text-muted">Support card subtitle</h6>
        </div-->

            <div class="text-center" style="margin: 10px;">
              <img
                width="50%"
                height="50%"
                src="data:image/png;base64,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"
                alt="icon" />
            </div>

            <ul class="list-group list-group-flush">
              {#if gameSession.character.backpack}
                {#await translate(gameSession.character.backpack)}
                  {#each gameSession.character.backpack as item}
                    {item}<br />
                  {/each}
                {:then translation}
                  {#each translation.translations as t, i}
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                      <span>
                        <img style="margin-right: 10px" src={t.png} alt="icon" />
                        {t.de}
                      </span>
                      <InfoPopup tooltip={t.tooltip} title={t.de} />
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
                  {#each gameSession.character.baseBackpack as item, i}
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
