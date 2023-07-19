<script>
  import { onMount } from "svelte";

  import Introduction from "./Introduction.svelte";
  import Welcome from "./Welcome.svelte";
  import Rules from "./Rules.svelte";
  import Adventure from "./Adventure.svelte";
  import Character from "./Character.svelte";

  /**
   * @type {any}
   */
  let gameSession;

  onMount(async () => {
    const res = await fetch("http://localhost:8080/session/", {
      method: "GET",
    });

    gameSession = await res.json();
  });
</script>

<div class="container">
  <div class="bs-docs-section clearfix">
    <div class="row" style="padding-top: 50px;">
      <div class="col-lg-12">
        <h2 id="nav-tabs">Einsamer Wolf</h2>

        <div class="bs-component" style="padding-top: 15px;">
          <ul class="nav nav-pills" role="tablist">
            <li class="nav-item" role="presentation">
              <a class="nav-link active" data-bs-toggle="tab" href="#cover" aria-selected="true" role="tab">Willkommen</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#cover" role="button" aria-haspopup="true" aria-expanded="false">Einleitung</a
              >
              <div class="dropdown-menu" style="">
                <a class="dropdown-item" data-bs-toggle="tab" href="#introduction">Was bisher geschah...</a>
                <a class="dropdown-item" data-bs-toggle="tab" href="#rules">Spielregeln</a>
                <!--div class="dropdown-divider" / -->
                <!--a class="dropdown-item" href="#">Separated link</a-->
              </div>
            </li>
            <!--li class="nav-item" role="presentation">
              <a class="nav-link" data-bs-toggle="tab" href="#intro" aria-selected="true" role="tab">Einleitung</a>
            </li -->
            <li class="nav-item" role="presentation">
              <a class="nav-link" data-bs-toggle="tab" href="#adventure" aria-selected="false" role="tab">Abenteuer</a>
            </li>
            <li class="nav-item" role="presentation">
              <a class="nav-link" data-bs-toggle="tab" href="#character" aria-selected="false" role="tab">Charakter</a>
            </li>
            <li class="nav-item" role="presentation">
              <a class="nav-link" data-bs-toggle="tab" href="#map" aria-selected="false" tabindex="-1" role="tab">Karte</a>
            </li>
          </ul>
          <hr />
        </div>
      </div>
    </div>
    
    <div id="myTabContent" class="tab-content">
      <div class="tab-pane fade show active" id="cover" role="tabpanel">
        <Welcome />
      </div>
      <div class="tab-pane fade" id="introduction" role="tabpanel">
        <Introduction />
      </div>
      <div class="tab-pane fade" id="rules" role="tabpanel">
        <Rules />
      </div>
      <div class="tab-pane fade" id="adventure" role="tabpanel">
        <Adventure bind:gameSession />
      </div>
      <div class="tab-pane fade" id="character" role="tabpanel">
        <Character bind:gameSession />
      </div>
      <div class="tab-pane fade" id="map" role="tabpanel">
        <p>
          <img src="map.png" alt="map" />
        </p>
      </div>
    </div>
  </div>
</div>
