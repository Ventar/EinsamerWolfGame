<script>
import { onMount } from "svelte";

  /**
   * 
   * @type {any}
   */
  export let gameSession;

  $: info = JSON.stringify(gameSession, null, 2);


  onMount(() => {
    


    
  });

</script>




<div class="row" style="margin-top: 70px;">
  <div class="col-lg-12">


    <button
    type="button"
    class="btn btn-secondary"
    data-bs-container="body"
    data-bs-toggle="popover"
    data-bs-placement="right"
    data-bs-content="Vivamus sagittis lacus vel augue laoreet rutrum faucibus."
    data-bs-original-title="Popover Title">Right</button
  >

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
