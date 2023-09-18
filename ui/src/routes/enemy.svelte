<script>
    import Icon from "./Icon.svelte";

    /**
     * @type {any}
     */
    export let enemy;
    

    
</script>

<div class="card border-danger mb-4">
    <div class="card-header">Gegner</div>
    <div class="card-body">
        <h4 class="card-title">{enemy.name}</h4>
        <div class="toast-body">
            <Icon value={enemy.endurance} img="health 24x24.png" text="Ausdauer" />
            <Icon value={enemy.battleStrength} img="biceps_24x24.png" text="Kampfstärke" />
        </div>
    </div>
</div>
