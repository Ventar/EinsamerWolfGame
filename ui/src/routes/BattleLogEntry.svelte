<script>
    import Character from "./Character.svelte";
    

    /**
     * @type {any}
     */
    export let entry;

    $: info = JSON.stringify(entry, null, 2);
</script>

<div
    class="toast show"
    style="--bs-toast-max-width: unset;"
    role="alert"
    aria-live="assertive"
    aria-atomic="true"
>
    <div class="toast-header">
        <strong class="me-auto">Kampfrunde {entry.battleRound}</strong>
        <small
            ><img src="dice_24x24.png" alt="dice" style="margin-right: 5px;" />
            {entry.dice}</small
        >
        <button
            type="button"
            class="btn-close ms-2 mb-1"
            data-bs-dismiss="toast"
            aria-label="Close"
        >
            <span aria-hidden="true" />
        </button>
    </div>
    <div class="toast-body">
        <table width="100%">
            <tr>
                <td width="20%"
                    ><img
                        src="health_24x24.png"
                        alt="health"
                        style="margin-right: 5px;"
                    />
                    {entry.characterEndurance}</td
                >

                <td width="20%" 
                    ><img
                        src="biceps_24x24.png"
                        alt="Kampfstärke"
                        style="margin-right: 5px;"
                    />Die kampfstärke beträgt&#160;{entry.battleStrength}</td
                ></tr
            ><tr>
                <td width="20%" />
            </tr>

            <tr>
                <td style="margin-top: 15px;"/>
                <img
                    src="weapon_skill.png"
                    alt="health"
                    style="margin-right: 5px;"
                />
                Waffenskill verwendet&#160;{entry.characterApplyWeaponSkill}

            </tr>
            <tr>
                <td />

                <td width="20%"
                    ><img
                        src="brain.png"
                        alt="health"
                        style="margin-right: 5px;"
                    />
                 Verwndet Gedankenstrahl&#160;{entry.characterUsesThoughtRay}</td
                >
            </tr>
        </table>
    </div>
</div>
