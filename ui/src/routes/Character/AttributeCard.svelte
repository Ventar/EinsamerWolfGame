<script>
  import IconCard from "./IconCard.svelte";

  /**
   * @type String
   */
  export let image;

  /**
   * @type String
   */
  export let name;

  /**
   * @type number
   */
  export let currentValue;

  /**
   * @type number
   */
  export let maxValue;

  /**
   * @param {number} max
   */
  function totalAttribute(max) {
    return { length: 50 - max };
  }

  /**
   * @param {number} current
   * @param {number} max
   */
  function lostAttribute(current, max) {
    return { length: max - current };
  }

  /**
   * @param {number} current
   */
  function currentAttribute(current) {
    return { length: current };
  }
</script>

<IconCard caption={name} src={image} cols={3}>
  <div class="card-body flexwrap">
    {#each currentAttribute(currentValue) as _}
      <div class="rcorners1" />
    {/each}
    {#each lostAttribute(currentValue, maxValue) as _}
      <div class="rcorners2" />
    {/each}
    {#each totalAttribute(maxValue) as _}
      <div class="rcorners3" />
    {/each}
  </div>
</IconCard>

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
  .rcorners3 {
    border-radius: 2px;
    background: #303030;
    margin: 2px;
    width: 15px;
    height: 15px;
  }
</style>
