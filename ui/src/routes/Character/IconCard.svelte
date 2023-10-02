<script>
  // ---------------------------------------------------------------------------------------
  // Input parameter
  // ---------------------------------------------------------------------------------------

  /**
   * The caption of the card
   * @type String
   */
  export let caption;

  /**
   * The content of the src attribute of the image tag for the header
   * @type String
   */
  export let src;

  /**
   * Nzmber of colums to cover.
   * @type Number
   */
  export let cols = 3;
</script>

<div class="col-lg-{cols}" style="display: flex; flex-grow: 1;">
  <div class="bs-component flexwrap">
    <div class="card mb-3 flexwrap">
      <div class="card-header">
        <img height="40" width="40" {src} alt={caption} />
        <span style="margin-left: 15px; font-weight: bold; align-items: center;">{caption}</span>
      </div>

      <slot />
    </div>
  </div>
</div>
