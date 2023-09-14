<script>
    /**
     * @type {any}
     */
    export let enemy;

    /**
     * @type {any}
     */
     export let entry;
</script>


<div class="col-4">
    <div class="card border-danger mb-4">
      <div class="card-header">Gegner</div>
      <div class="card-body">
        <h4 class="card-title">{enemy.name}</h4>
        <div class="toast-body">
            <table width=100%>
                <tr>
                </table>
                <td width="20%"><img src="health_24x24.png" alt="health" style="margin-right: 5px;" /> {entry.enemy.endurance}</td>
            
            </div>
        </div>
    
        <br />
        <p class="card-text">
          Ausdauer: <strong
            ><span class="text-success">
              {enemy.endurance}
            </span></strong
          ><br />
          Kampfstärke:
          <strong
            ><span class="text-success">
              {enemy.battleStrength}</span
            ></strong
          >
        </p>
      </div>
    </div>
  