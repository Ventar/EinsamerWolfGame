<script>
  /**
   * @type {any}
   */
  export let gameSession;

  $: info = JSON.stringify(gameSession, null, 2);
</script>

<div class="overflow-y-auto" style="max-height: 150px;">
  <pre>
    {info}
  </pre>
</div>
