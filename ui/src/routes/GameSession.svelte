<script>
  /**
   * @type {any}
   */
  export let gameSession;

  $: info = JSON.stringify(gameSession, null, 2);
</script>


<pre>
    {info}
</pre>
