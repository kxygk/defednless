;; == `Libraries`
;; This allows for a single file excutable +.clj+ file

(add-libs {'org.asciidoctor/asciidoctorj-revealjs {:mvn/version "5.0.0.rc1"}
           'org.asciidoctor/asciidoctorj-pdf      {:mvn/version "1.6.2"}
           'org.asciidoctor/asciidoctorj          {:mvn/version "2.5.3"}})

(import [org.asciidoctor
         Asciidoctor
         OptionsBuilder
         SafeMode])

(let [filestr
      "/home/kxygk/Projects/stars/Zeeden2023.adoc"
      #_
      "/home/kxygk/Projects/stars/agu2023.adoc"
      #_
      "/home/kxygk/Projects/stars/tga2022.adoc"
      #_
      "/home/kxygk/Projects/stars/summer-plans.adoc"
      #_
      "/home/kxygk/Projects/mlfinal/report.adoc"
      #_
      "/home/kxygk/Projects/class/montecarlo.adoc"
      #_
      "/home/kxygk/Projects/class/montecarlo.adoc"
      #_
      "/home/kxygk/Projects/stars/test.adoc"
      #_
      "/home/kxygk/Projects/stars/seminar3-slides.adoc"
      #_
      "/home/kxygk/Projects/stars/bangkok-phd.adoc"
      #_
      "/home/kxygk/Projects/stars/olimio180.adoc"
      #_
      "/home/kxygk/Projects/stars/deepco2.adoc"
      input-file    (clojure.java.io/file filestr)
      adoctor       (org.asciidoctor.Asciidoctor$Factory/create)
      reveal-option (doto (org.asciidoctor.OptionsBuilder/options)
                      (.backend "revealjs")
                      (.safe org.asciidoctor.SafeMode/UNSAFE)
                      (.attributes (.attribute (org.asciidoctor.AttributesBuilder/attributes)
                                               "revealjsdir"
                                               "../reveal.js")))]
  (.requireLibrary adoctor
                   (into-array String
                               ["asciidoctor-revealjs"]))
  (.convertFile adoctor
                input-file
                reveal-option))
 
