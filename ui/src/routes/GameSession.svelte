<script>
  /**
   *
   * @type {any}
   */
  export let gameSession;

  $: info = JSON.stringify(gameSession, null, 2);
</script>

<pre style="margin-top: 90px;">
  {info}
</pre>
