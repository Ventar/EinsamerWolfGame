<script>
    /**
     * @type {any}
     */
    export let value = "";

    /**
     * @type Boolean
     */
    export let visible = true;

    /**
     * @type {any}
     */
    export let img;

    /**
     * @type String
     */
    export let text;
</script>

{#if visible}
    <div style="margin-top: 5px;">
        {#if value !== ""}
            <img src={img} alt={img} style="margin-right: 5px;" />{text}&#160;{value}
        {:else}
            <img src={img} alt={img} style="margin-right: 5px;" />{text}
        {/if}
    </div>
{/if
}
