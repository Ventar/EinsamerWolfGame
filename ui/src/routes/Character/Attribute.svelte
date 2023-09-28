<script>
  // @ts-nocheck

  /**
   * @type String
   */
  export let image;

  /**
   * @type String
   */
  export let name;

  /**
   * @type int
   */
  export let currentValue;

  /**
   * @type int
   */
  export let maxValue;

  const initpopup = (c) => {
    //console.log(c);

    new bootstrap.Popover(c, { html: true });
  };

  function totalAttribute(max) {
    return { length: 50 - max };
  }

  function lostAttribute(current, max) {
    return { length: max - current };
  }

  function currentAttribute(current) {
    return { length: current };
  }
</script>

<div class="col-lg-3">
  <div class="bs-component">
    <div class="card mb-3">
      <div class="card-header">
        <div class="col-sm-8" style="display: flex; align-items: center; font-size: larger;">
          <img src={image} alt="icon" />
          <span style="margin-left: 15px; font-weight: bold;"> {name}</span>
        </div>
      </div>
      <div class="card-body" style="display: flex; flex-wrap: wrap;">
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
    </div>
  </div>
</div>

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
