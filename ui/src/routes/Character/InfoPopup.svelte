<script>
    // @ts-nocheck

    /**
     * @type String
     */
    export let title;

    /**
     * @type String
     */
    export let tooltip;

    const initpopup = (c) => {
        //console.log(c);

        new bootstrap.Popover(c, { html: true });
    };
</script>

<button
    use:initpopup={this}
    type="button"
    class="btn btn-dark btn-sm"
    data-bs-container="body"
    data-bs-toggle="popover"
    data-bs-trigger="focus"
    data-bs-placement="right"
    data-bs-content={tooltip}
    data-bs-original-title={title}>
    <img width="16px" src="info-16.png" alt="Deko 1" />
</button>
