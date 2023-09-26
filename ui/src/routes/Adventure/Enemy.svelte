<script>
    import Icon from "../Common/Icon.svelte";

    /**
     * @type {any}
     */
    export let enemy;
</script>

{#if enemy.endurance > 0}
    <div class="card border-light mb-4">
        <div class="card-header">{enemy.name}</div>
        <div class="card-body">
            <!--h4 class="card-title"></h4-->
            <div class="toast-body">
                <Icon value={enemy.endurance} img="health 24x24.png" text="Ausdauer" />
                <Icon value={enemy.battleStrength} img="biceps_24x24.png" text="Kampfstärke" />
            </div>
        </div>
    </div>
{/if}
