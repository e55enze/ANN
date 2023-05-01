
(deffunction print-info (?pos)
    (printout t "Pos of fact: " ?pos crlf 
	"dog breed: " (fact-slot-value ?pos name) crlf 
	"hair length: " (fact-slot-value ?pos hair_length) " "crlf
	"character: " (fact-slot-value ?pos charact) crlf
	)
)

(deffunction quest-about-ears ()
	(printout t crlf "Dog's ears short or long ?" crlf
	"Choose: (short/long)" crlf)
)

(deffunction quest-about-weight (?w)
	(printout t crlf "Dog's weight less " ?w " kg or more??" crlf
	"Choose: (less/more)" crlf)
)
(deffunction quest-about-height (?h)
	(printout t crlf "Dog's height less " ?h " kg or more??" crlf
	"Choose: (less/more)" crlf)
)

(defrule dogs-hair-length
	(not (breed ?))
	(not (hair ?))
	=>
	(printout t crlf "Long-hair or short-hair breed? " crlf
		"Choose: (short/long)" crlf)
	(bind ?hair (read))
	(assert (hair ?hair))
)

(defrule dogs-height-50
	(not (breed ?))
	(not (height_50 ?))
	=>
	(printout t crlf "Dog's height less 50 centimeters or more ?" crlf
		"Choose: (less/more)" crlf)
	(bind ?height_50 (read))
	(assert (height_50 ?height_50))
)

(defrule dogs-height-70
	(not (breed ?))
	(hair long)
	(height_50 more)
	=>
	(printout t crlf "Dog's height less 70 centimeters or more ?" crlf
		"Choose: (less/more)" crlf)
	(bind ?height_70 (read))
	(assert (height_70 ?height_70))
)

(defrule dogs-tail
	(not (breed ?))
	(hair short)
	(height_50 less)
	=>
	(printout t crlf "Dog's tail short or long?" crlf
		"Choose: (short/long)" crlf)
	(bind ?tail (read))
	(assert (tail ?tail))
)

(defrule weight-50-kg
	(not (breed ?))
	(hair short)
	(height_50 more)
	=>
	(quest-about-weight 50)
	(bind ?weight_50 (read))
	(assert (weight_50 ?weight_50))
)

(defrule dogs-ears
	(not (breed ?))
	(or (height_50 less)
		(height_50 more)
	)
	(or (and (tail long)
			(hair short)
		)
		(hair long)
	)
	=>
	(printout t crlf "Dog's ears short or long ?" crlf
	"Choose: (short/long)" crlf)
	(bind ?ears (read))
	(assert (ears ?ears))
)


(defrule char-color
	(not (breed ?))
	(hair long)
	(height_50 more)
	(height_70 more)
	=>
	(printout t crlf "Is the dog uniform or mixed color??" crlf
	"Choose: (self/mixed)" crlf)
	(bind ?char_color (read))
	(assert (char_color ?char_color))
)

(defrule tone-color
	(not (breed ?))
	(hair long)
	(height_50 more)
	(or (height_70 less)
		(height_70 more)
	)
	(or (ears long)
		(char_color self)
	)

	=>
	(printout t crlf "The dog has a tone color bright or dark ?" crlf
	"Choose: (bright/dark)" crlf)
	(bind ?tone_color (read))
	(assert (tone_color ?tone_color))
)


(defrule weight-30-kg
	(not (breed ?))
	(hair long)
	(height_50 more)
	(height_70 less)
	(ears short)
	=>
	(quest-about-weight 30)
	(bind ?weight_30 (read))
	(assert (weight_30 ?weight_30))
)

(defrule body-length
	(not (breed ?))
	(hair short)
	(height_50 less)
	(tail long)
	(ears short)
	=>
	(printout t crlf "The body long or short?" crlf
	"Choose: (short/long)" crlf)
	(bind ?body_length (read))
	(assert (body_length ?body_length))
)

(defrule dogs-character-friendly
	(not (breed ?))
	(hair long)
	(height_50 less)
	=>
	(printout t crlf "Is the dog's character friendly?" crlf
	"Choose: (yes/no)" crlf)
	(bind ?friendly (read))
	(assert (friendly  ?friendly))
)

(defrule Bulldog
	(not (breed ?))
	(hair short)
	(height_50 less)
	(tail short)
	=>
	(assert (breed "name: Bulldog, type: short-haired breed, character: friendly, obedient"))
)
(defrule Hound
	(not (breed ?))
	(hair short)
	(height_50 less)
	(tail long)
	(ears long)
	=>
	(assert (breed "name: Hound, type: short-haired breed, character: calm, friendly"))
)
(defrule Pug
	(not (breed ?))
	(hair short)
	(height_50 less)
	(tail long)
	(ears short)
	(body_length short)
	=>
	(assert (breed "name: Pug, type: short-haired breed, character:smart, obedient"))
)
(defrule Chihuahua
	(not (breed ?))
	(hair short)
	(height_50 less)
	(tail long)
	(ears short)
	(body_length long)
	=>
	(assert (breed "name: Chihuahua, type: short-haired breed, character: bold, active"))
)
(defrule Great-Dane
	(not (breed ?))
	(hair short)
	(height_50 more)
	(weight_50 more)
	=>
	(assert (breed "name: Great_Dane, type: short-haired breed, character: friendly, easily trained"))
)
(defrule Foxhound
	(not (breed ?))
	(hair short)
	(height_50 more)
	(weight_50 less)
	=>
	(assert (breed "name: Foxhound, type: short-haired breed, character: active, friendly"))
)
(defrule Cocker-Spaniel
	(not (breed ?))
	(hair long)
	(height_50 less)
	(ears long)
	(friendly yes)
	=>
	(assert (breed "name: Cocker_Spaniel, type: long-haired breed, character: calm, playful"))
)
(defrule Dachshund
	(not (breed ?))
	(hair long)
	(height_50 less)
	(ears long)
	(friendly no)
	=>
	(assert (breed "name: Dachshund, type: long-haired breed, character: bold, stubborn"))
)	

(defrule Yorkshire-Terrier
	(not (breed ?))
	(hair long)
	(height_50 less)
	(ears short)
	(friendly yes)
	=>
	(assert (breed "name: Yorkshire-Terrier, type: long-haired breed, character: Friendly"))
)	

(defrule Chow-Chow
	(not (breed ?))
	(hair long)
	(height_50 less)
	(ears short)
	(friendly no)
	=>
	(assert (breed "name: Chow-Chow, type: long-haired breed, character: unfriendly, devotee"))
)
(defrule Grand-Basset-Griffon-Vendeeng
	(not (breed ?))
	(hair long)
	(height_50 more)
	(height_70 less)
	(ears long)
	(tone_color bright)
	=>
	(assert (breed "name: Grand Basset Griffon Vendeeng, type: long-haired breed, character: active, friendly"))
)
(defrule Irish-Setter
	(not (breed ?))
	(hair long)
	(height_50 more)
	(height_70 less)
	(ears long)
	(tone_color dark)
	=>
	(assert (breed "name: Irish_Setter, type: long-haired breed, character: energetic, independent"))
)
(defrule Collie
	(not (breed ?))
	(hair long)
	(height_50 more)
	(height_70 less)
	(ears short)
	(weight_30 less)
	=>
	(assert (breed "name: Collie, type: long-haired breed, character: smart, active"))
)
(defrule Caucasian-Shepherd-Dog
	(not (breed ?))
	(hair long)
	(height_50 more)
	(height_70 less)
	(ears short)
	(weight_30 more)
	=>
	(assert (breed "name: Caucasian Shepherd Dog, type: long-haired breed, character: intolerant, vigilant"))
)
(defrule Irish-Wolfhound
	(not (breed ?))
	(hair long)
	(height_50 more)
	(height_70 more)
	(char_color self)
	(tone_color bright)
	=>
	(assert (breed "name: Irish_Wolfhound, type: long-haired breed, character: kind, energetic"))
)
(defrule Newfoundland
	(not (breed ?))
	(hair long)
	(height_50 more)
	(height_70 more)
	(char_color self)
	(tone_color dark)
	=>
	(assert (breed "name: Newfoundland, type: long-haired breed, character: friendly, easily trained"))
)
(defrule Saint-Bernard
	(not (breed ?))
	(hair long)
	(height_50 more)
	(height_70 more)
	(char_color mixed)
	=>
	(assert (breed "name: Saint Bernard, type: long-haired breed, character: friendly, calm"))
)

(defrule print-breed ""

  (breed ?item)
  =>
  (printout t crlf crlf)
  (printout t "Suggested breed:")
  (printout t crlf crlf)
  (format t " %s%n%n%n" ?item)
)