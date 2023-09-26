<script>
  import { onMount } from "svelte";
  import { page } from "$app/stores";

  import Introduction from "./Introduction.svelte";
  import Welcome from "./Welcome.svelte";
  import Rules from "./Rules.svelte";
  import Adventure from "./Adventure/Adventure.svelte";
  import Character from "./Character/Character.svelte";
  import GameSession from "./GameSession.svelte";

  /**
   * @type {any}
   */
  let gameSession;
  let host = "";
  $: host, (host = $page.url.hostname);

  const characterName = $page.url.searchParams.get("character");
  const adventureActive = $page.url.searchParams.get("showAdventure");

  onMount(async () => {
    console.log("1 - " + characterName);

    let res;

    if (characterName) {
      res = await fetch("http://" + host + ":8080/session/load/", {
        method: "POST",
        body: JSON.stringify({
          name: characterName,
        }),
      });
    } else {
      res = await fetch("http://" + host + ":8080/session/", {
        method: "GET",
      });
    }

    gameSession = await res.json();
  });
</script>

<nav class="navbar navbar-expand-lg fixed-top bg-dark" data-bs-theme="dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#home">Einsamer Wolf</a>
    <button
      class="navbar-toggler"
      type="button"
      data-bs-toggle="collapse"
      data-bs-target="#navbar"
      aria-controls="navbar"
      aria-expanded="false"
      aria-label="Toggle navigation">
      <span class="navbar-toggler-icon" />
    </button>

    <div class="collapse navbar-collapse" id="navbar">
      <ul class="navbar-nav me-auto" role="tablist">
        <li class="nav-item">
          <a
            id="navHome"
            class:active={!adventureActive}
            class="nav-link"
            data-bs-toggle="tab"
            href="#home"
            aria-selected="true"
            aria-controls="home"
            role="tab">Willkommen</a>
        </li>
        <li class="nav-item dropdown">
          <a
            class="nav-link dropdown-toggle"
            data-bs-toggle="dropdown"
            href="#"
            role="button"
            aria-haspopup="true"
            aria-expanded="false">Regeln</a>
          <div class="dropdown-menu">
            <a class="dropdown-item" data-bs-toggle="tab" href="#introduction">Was bisher geschah...</a>
            <a class="dropdown-item" data-bs-toggle="tab" href="#rules">Spielregeln</a>
          </div>
        </li>
        <li class="nav-item">
          <a
            id="navAdventure"
            class:active={adventureActive}
            class="nav-link"
            data-bs-toggle="tab"
            href="#adventure"
            aria-selected="false"
            aria-controls="adventure"
            role="tab">Abenteuer</a>
        </li>
        <li class="nav-item">
          <a
            id="navCharacter"
            class="nav-link"
            data-bs-toggle="tab"
            href="#character"
            aria-selected="false"
            aria-controls="character"
            role="tab">Charakter</a>
        </li>
        <li class="nav-item">
          <a
            id="navMap"
            class="nav-link"
            data-bs-toggle="tab"
            href="#map"
            aria-selected="false"
            aria-controls="map"
            role="tab">Karte</a>
        </li>
        <li class="nav-item">
          <a
            id="navGameSession"
            class="nav-link"
            data-bs-toggle="tab"
            href="#gamesession"
            aria-selected="false"
            aria-controls="gamesession"
            role="tab">Debug Session Data</a>
        </li>
        <!--li class="nav-item">
          <a class="nav-link" href="#">Pricing</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">About</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Dropdown</a>
          <div class="dropdown-menu">
            <a class="dropdown-item" href="#">Action</a>
            <a class="dropdown-item" href="#">Another action</a>
            <a class="dropdown-item" href="#">Something else here</a>
            <div class="dropdown-divider" />
            <a class="dropdown-item" href="#">Separated link</a>
          </div>
        </li -->
      </ul>
    </div>
  </div>
</nav>

<div class="container">
  <div id="content" class="tab-content">
    <div
      class:active={!adventureActive}
      class:show={!adventureActive}
      class="tab-pane fade"
      id="home"
      role="tabpanel"
      aria-labelledby="navHome">
      <Welcome />
    </div>
    <div class="tab-pane fade" id="introduction" role="tabpanel" aria-labelledby="navAdventure">
      <Introduction />
    </div>
    <div class="tab-pane fade" id="rules" role="tabpanel">
      <Rules />
    </div>
    <div
      class:active={adventureActive}
      class:show={adventureActive}
      class="tab-pane fade"
      id="adventure"
      role="tabpanel">
      <Adventure bind:gameSession bind:host />
    </div>
    <div class="tab-pane fade" id="character" role="tabpanel" aria-labelledby="navCharacter">
      <Character bind:gameSession />
    </div>
    <div class="tab-pane fade" id="gamesession" role="tabpanel" aria-labelledby="navGameSession">
      <GameSession bind:gameSession />
    </div>
    <div class="tab-pane fade" id="map" role="tabpanel" aria-labelledby="navMap">
      <p>
        <img width="100%" src="map.png" alt="map" />
      </p>
    </div>
  </div>
</div>
