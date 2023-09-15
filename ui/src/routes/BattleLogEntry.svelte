<script>
    import Icon from "./Icon.svelte";
    
    /**
     * @type {any}
     */
    export let entry;
</script>

<div class="toast show" style="--bs-toast-max-width: unset;" role="alert" aria-live="assertive" aria-atomic="true">
    <div class="toast-header">
        <strong class="me-auto">Kampfrunde {entry.battleRound}</strong>
        <small>
            <img src="dice_24x24.png" alt="dice" style="margin-right: 5px;" />
            {entry.dice}
        </small>
        <button type="button" class="btn-close ms-2 mb-1" data-bs-dismiss="toast" aria-label="Close">
            <span aria-hidden="true" />
        </button>
    </div>
    <div class="toast-body">
        <div class="row">
            <div class="col-4">
                <Icon value={entry.characterEndurance} img="health_24x24.png" text="Ausdauer" />
            </div>
            <div class="col-8">
                <Icon value={entry.battleStrength} img="biceps_24x24.png" text="Basiskampfstärke" />
                <Icon visible={entry.characterApplyWeaponSkill} img="weapon_skill_24x24.png" text="Waffenskill (+2)" />
                <Icon visible={entry.characterUsesThoughtRay} img="brain_24x24.png" text="Gedankenstrahl (+2)" />
                <hr />
                <Icon visible={entry.characterUsesThoughtRay} img="brain_24x24.png" text="Gesamtkampfstärke" />
            </div>
        </div>
    </div>
</div>
