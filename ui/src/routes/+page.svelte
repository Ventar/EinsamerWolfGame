<script>
  import Introduction from "./Introduction.svelte";
  import Welcome from "./Welcome.svelte";
  import Rules from "./Rules.svelte";
</script>

<div class="container">
  <div class="bs-docs-section clearfix">
    <div class="row" style="padding-top: 50px;">
      <div class="col-lg-12">
        <h2 id="nav-tabs">Einsamer Wolf</h2>

        <div class="bs-component" style="padding-top: 15px;">
          <ul class="nav nav-pills" role="tablist">
            <li class="nav-item" role="presentation">
              <a class="nav-link active" data-bs-toggle="tab" href="#cover" aria-selected="true" role="tab">Willkommen</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#cover" role="button" aria-haspopup="true" aria-expanded="false">Einleitung</a
              >
              <div class="dropdown-menu" style="">
                <a class="dropdown-item" data-bs-toggle="tab" href="#introduction">Was bisher geschah...</a>
                <a class="dropdown-item" data-bs-toggle="tab" href="#rules">Spielregeln</a>
                <!--div class="dropdown-divider" / -->
                <!--a class="dropdown-item" href="#">Separated link</a-->
              </div>
            </li>
            <!--li class="nav-item" role="presentation">
              <a class="nav-link" data-bs-toggle="tab" href="#intro" aria-selected="true" role="tab">Einleitung</a>
            </li -->
            <li class="nav-item" role="presentation">
              <a class="nav-link" data-bs-toggle="tab" href="#adventure" aria-selected="false" role="tab">Abenteuer</a>
            </li>
            <li class="nav-item" role="presentation">
              <a class="nav-link" data-bs-toggle="tab" href="#map" aria-selected="false" tabindex="-1" role="tab">Karte</a>
            </li>
          </ul>
          <hr/>

          <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade show active" id="cover" role="tabpanel">
              <Welcome />
            </div>
            <div class="tab-pane fade" id="introduction" role="tabpanel">
              <Introduction />
            </div>
            <div class="tab-pane fade" id="rules" role="tabpanel">
              <Rules />
            </div>
            <div class="tab-pane fade" id="adventure" role="tabpanel">
              <p style="padding-top: 25px;">
                Du wirst vom Läuten deines mechanischen Weckers aus dem Schlaf gerissen, den du am Vorabend dicht neben deinem Kissen platziert hast. Im
                dämmrigen Kerzenlicht des Dormitoriums dem Schlafsaal der Initianden erhebst du dich von deiner Schlafstätte und ziehst dir schnell deine grüne
                Kai-Tunika und den Umhang an. Kurz hältst du inne, um deinen Gürtel und den Rucksack zu richten, bevor du deine schlafenden Kameraden verlässt.
                Du hast dir fest vorgenommen, nicht zu spät zur Trainingsstunde von Kai-Meister Sternenfeuer zu kommen, denn du weißt, welche Strafe dir droht,
                wenn du dein Versprechen brichst und unpünktlich bist.
              </p>
              <p>
                Schnell und leise verlässt du das Dormitorium durch die Nordtür, die direkt auf das Übungsgelände im Zentrum der Kai-Abtei führt.Sternenfeuer
                erwartet dich bereits. Er trägt jetzt schon die feierliche zeremonielle Robe für das abendliche Fest. Im düsteren Licht, das jetzt kurz vor
                Sonnenaufgang herrscht, kannst du nur dank deiner scharfen Augen deinen Meister diesen hervorragenden Krieger neben einem Waffengestell
                erkennen. Er begrüßt dich mit einem kurzen Nicken und befiehlt dir, eine der hölzernen Übungswaffen aus dem Gestell zu nehmen. Du wählst ein
                Schwert aus guter sommerlendischer Eiche und nimmst deine Position ihm gegenüber ein bereit, das Training zu beginnen.
              </p>
              <p>
                „Kämpfe werden nicht immer im hellen Tageslicht geführt, Lautloser Wolf“, sagt er und nimmt eine hölzerne Axt aus der Waffenhalterung. „Die
                Dunkelheit an diesem Morgen wird dazu dienen, deine Sinne zu schärfen. Gleiches lässt sich leider nicht von deiner Aufmerksamkeit in der Klasse
                sagen.“ Geduldig führt dich dein Kai-Meister durch die Übungen, zeigt dir die korrekten Paraden und wie du auf bestmögliche Weise angreifst.
                Nach einer halben Stunde intensiven Trainings bist du schweißüberströmt und völlig außer Atem, trotz der frostigen Kühle der Morgenluft. Meister
                Sternenfeuer dagegen zeigt nicht die geringsten Anzeichen von Müdigkeit nach diesem anstrengenden Training. „Gut gemacht, Lautloser Wolf! Du
                kannst dein Schwert jetzt ablegen und dich eine Weile ausruhen. Deine Unzulänglichkeiten im Klassenzimmer scheinst du durch deine angeborenen
                Fähigkeiten im Umgang mit der Waffe gut ausgleichen zu können. Du hast viel Talent. Würdest du nur die gleiche Hingabe bei deinen Studien
                zeigen, könntest du eines Tages bestimmt den Rang eines Kai- Meisters erlangen.“ Du dankst Sternenfeuer für dieses Kompliment und seine
                Ratschläge. Er lobt nie ohne Grund, und so erfüllen dich seine Worte mit der festen Absicht, in Zukunft nicht mehr deinen Tagträumen
                nachzuhängen. Als du dein Schwert in das Waffengestell zurückgestellt hast und gerade den ersten kühlen Schluck aus deiner Wasserflasche nimmst,
                wird die morgendliche Stille jäh durch das stürmische Läuten der Turmglocke des Torhauses zerrissen.
              </p>
              <p>
                Es ist die Alarmglocke der Abtei. „Bei der Liebe Ishirs …!“, stößt Sternenfeuer hervor. Er blickt angestrengt nach Westen, wo der Himmel auf
                seltsame und unheimliche Art mit jedem Moment dunkler wird. Zunächst kannst du in der Düsternis nichts erkennen, doch dann siehst du Bewegung in
                der Finsternis. Hunderte von großen geflügelten Kreaturen nähern sich der Abtei! „Kraan!“, keucht Sternenfeuer. „Kai behüte uns!“ Der
                unheilvolle Klang der Alarmglocke hat inzwischen jeden in der Abtei geweckt. Einige Kai-Meister und viele unerfahrene Kai-Krieger stürmen aus
                dem Dormitorium. Die meisten von ihnen tragen keine Rüstung, aber jeder ist bewaffnet und bereit, die Festung gegen den überraschenden Angriff
                zu verteidigen. Die erste Angriffswelle der geflügelten Kraan schießt vom düsteren Himmel herab. Als sie sich kreischend dem mächtigen Torhaus
                der Abtei nähern, siehst du, dass sie auf ihren ledernen Rücken Dutzende von Giak transportieren, grausame Kreaturen, die das Rückgrat der
                dunklen Armee der Schwarzen Lords bilden. Was Giak an körperlicher Größe und Intelligenz entbehren, gleichen sie durch schiere Masse wieder aus.
                Die Tore des Torhauses stehen noch halb offen, als die ersten Kraan ihre Reiter abspringen lassen und kehrtmachen. Diese ersten wenigen Giak
                rappeln sich auf und hasten auf den Eingang zu, um sich Zugang zum Torhaus zu verschaffen, bevor dies vollständig verschlossen wird. „Lautloser
                Wolf, folge mir!“, befiehlt Sternenfeuer, wendet sich zum Torhaus und läuft auf den Eingang zu.
              </p>
            </div>
            <div class="tab-pane fade" id="map" role="tabpanel">
              <p>
                <img src="map.png" alt="map" />
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
