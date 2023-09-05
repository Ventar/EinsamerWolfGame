<script>
  /**
   * @type {any}
   */
  export let gameSession;

  $: info = JSON.stringify(gameSession, null, 2);
</script>

<!--div class="overflow-y-auto" style="max-height: 600px;"-->
<button type="submit" class="btn btn-primary" >
  <i class="bi bi-cloud-arrow-up-fill"></i>  Speichern 
</button>

<button type="submit" class="btn btn-primary">
  <i class="bi bi-cloud-arrow-down-fill"></i>  Laden
</button>

<button type="submit" class="btn btn-primary">
  <i class="bi bi-cloud-check-fill"></i>  Neues Abenteuer
</button>

<div class="btn-group  mr-2" role="group" aria-label="Basic example">
  <button type="button" class="btn btn-secondary">Left</button>
  <button type="button" class="btn btn-secondary">Middle</button>
  <button type="button" class="btn btn-secondary">Right</button>
</div>

  <pre>
    {info}
  </pre>

