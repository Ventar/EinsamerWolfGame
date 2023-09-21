<script>
    import Character from "./Character.svelte";
    import Icon from "./Icon.svelte";

    /**
     * @type {any}
     */
    export let entry;

    /**
     * @type {any}
     */
    export let gameSession;
</script>

<div class="toast show" style="--bs-toast-max-width: unset;" role="alert" aria-live="assertive" aria-atomic="true">
    <div class="toast-header" style="background-color: #666666;">
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
            <div class="col-6">
                <table>
                    <tr>
                        <td ><img src="health 24x24.png" alt="health 24x24.png" style="margin-right: 10px;" /></td>
                        <td style="padding-right: 15px;">Ausdauer</td>
                        <td style="text-align: right;">{entry.characterEndurance}</td>
                    </tr>
                    <tr>
                        <td style="padding-top: 5px;"><img src="battleQuotient.png" alt="battleQuotient.png" style="margin-right: 10px;" /></td>
                        <td style="padding-right: 15px; padding-top: 5px;">Spieler Schaden</td>
                        <td style="text-align: right; padding-top: 5px;"><span><b>{entry.battleValue.character}</b></span></td>
                    </tr>
                    <tr>
                        <td style="padding-top: 5px;"><img src="punch-blast.png" alt="punch-blast.png" style="margin-right: 10px;" /></td>
                        <td style="padding-right: 15px; padding-top: 5px;">Gegner Schaden</td>
                        <td style="text-align: right; padding-top: 5px;"><span><b>{entry.battleValue.enemy}</b></span></td>
                    </tr>
                </table>
            </div>

            <div class="col-6">
                <table>
                    <tr>
                        <td><img src="biceps_24x24.png" alt="biceps_24x24.png" style="margin-right: 10px;" /></td>
                        <td style="padding-right: 15px;">Basiskampfstärke</td>
                        <td style="text-align: right;">{gameSession.character.battleStrength.value}</td>
                    </tr>
                    <tr>
                        <td style="padding-top: 5px;"><img src="weapon_skill_24x24.png" alt="weapon_skill_24x24.png" style="margin-right: 10px;" /></td>
                        <td style="padding-top: 5px; padding-right: 15px;"> Waffenskill</td>
                        <td style="padding-top: 5px; text-align: right;">+2</td>
                    </tr>
                    <tr>
                        <!-- {entry.characterUsesThoughtRay}-->
                        <td style="padding-top: 5px; padding-bottom: 15px;"><img src="thoughtRay.png" alt="thoughtRay.png" style="margin-right: 5px;" /></td>
                        <td style="padding-top: 5px; padding-bottom: 15px; padding-right: 15px;">Gedankenstrahl </td>
                        <td style="padding-top: 5px; padding-bottom: 15px; text-align: right;">+ 2</td>
                    </tr>
                    <tr>
                        <td
                            style="border-top: white; border-top-width: thin; border-top-color: white; border-top-style: inset; padding-top: 5px;"
                            ><img src="biceps_24x24.png" alt="biceps_24x24.png" style="margin-right: 10px;" /></td>
                        <td
                            style="border-top: white; border-top-width: thin; border-top-color: white; border-top-style: inset; padding-top: 5px; padding-right: 15px;"
                            >Gesamtkampfstärke</td>
                        <td
                            style="border-top: white; border-top-width: thin; border-top-color: white; border-top-style: inset; padding-top: 5px; text-align: right;"
                            >{entry.battleStrength}</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
