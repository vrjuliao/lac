# Matching HAPI with GDPR
The General Data Protection regulation is available at\
[https://gdpr.eu/article-1-subject-matter-and-objectives-overview/](https://gdpr.eu/article-1-subject-matter-and-objectives-overview/)


### Article 5
[https://gdpr.eu/article-5-how-to-process-personal-data](https://gdpr.eu/article-5-how-to-process-personal-data)

Paragraph 2: "The controller shall be responsible for, and be able to
demonstrate compliance with, paragraph 1 (‘accountability’)."\
- A way to demonstrate how the data is processed (as requested in the
  paragraph **1.e**) is using tools that are capable to show for users and
  for lawyers how the data is accessed, and which entities can process that
  data.


### Article 12
[https://gdpr.eu/article-12-how-controllers-should-provide-personal-data-to-the-subject](https://gdpr.eu/article-12-how-controllers-should-provide-personal-data-to-the-subject)

Paragraph 7: "The information to be provided to data subjects pursuant to
Articles 13 and 14 may be provided in combination with standardised icons in
order to give in an easily visible, intelligible and clearly legible manner a
meaningful overview of the intended processing. Where the icons are presented
electronically they shall be machine-readable."
- A easy-to-read language can be a standardised way to provide such data
  subject consulting.


### Article 13
[https://gdpr.eu/article-13-personal-data-collected/](https://gdpr.eu/article-13-personal-data-collected/)

Paragraph 1: "Where personal data relating to a data subject are collected from
the data subject, the controller shall, at the time when personal data are
obtained, provide the data subject with all of the following information:"\
Letter e: "the recipients or categories of recipients of the personal data, if
any;"

- A policy in Hapi demands that data being represented as group of entities.
  So, its easy to get the recipients and the group os recipients where data
  subject is declared in Hapi.

### Article 15
[https://gdpr.eu/article-15-right-of-access](https://gdpr.eu/article-15-right-of-access)

The whole article concerns about the user being capable to obtain their own
data in the *controller* stores.
A policy in Hapi may relates which data is accessed.

It's possible to give an example related to the *Paragraph 2*, where we can
exemplify a **Data Country** stating which group of countries have access of
that data.
Since the paragraph 2 demands that the *data subject* must be informed about
the abroad countries that are accessing its information, a policy in Hapi is
capable to provide this information.


### Article 20
[https://gdpr.eu/article-20-right-to-data-portability/](https://gdpr.eu/article-20-right-to-data-portability/)

Paragraph 1:
"The data subject shall have the right to receive the personal data concerning
him or her, which he or she has provided to a controller, in a structured,
commonly used and machine-readable format and have the right to transmit those
data to another controller without hindrance from the controller to which the
personal data have been provided[...]"

- Currently there is not a pattern well defined about how data is shared
  between systems.
  Hapi can be an alternative to fill this lack.

### Article 25
[https://gdpr.eu/article-25-data-protection-by-design](https://gdpr.eu/article-25-data-protection-by-design)

Paragraph 2:
"The controller shall implement appropriate technical and organisational
measures for ensuring that, by default, only personal data which are necessary
for each specific purpose of the processing are processed.
That obligation applies to the amount of personal data collected, the extent of
their processing, the period of their storage and their accessibility.
In particular, such measures shall ensure that by default personal data are not
made accessible without the individual’s intervention to an indefinite number
of natural persons."

- In Hapi we can state a policy that starts with:
    ```
    DENY 
    EXCEPT {
      ...
    }
    ```
  what means that all access start denied by default, and the policy writer
  (the *controller*) shall specifies which group of entities can form a Entity
  of Access.
  This approach complies with what is demanded by regulations.

Paragraph 3:
"An approved certification mechanism pursuant to Article 42 may be used as an
element to demonstrate compliance with the requirements set out in paragraphs 1
and 2 of this Article."

- The certification mechanisms can use Hapi to express a policy and then, check
  if a company data processing is according to what is requested.

### Article 38
[https://gdpr.eu/article-38-data-protection-officer](https://gdpr.eu/article-38-data-protection-officer)

Paragraph 3:
"The controller and processor shall ensure that the data protection officer does
not receive any instructions regarding the exercise of those tasks.
He or she shall not be dismissed or penalised by the controller or the
processor for performing his tasks. The data protection officer shall directly
report to the highest management level of the controller or the processor."

- This kind of policy can be written in Hapi.
  Since we declare the *controller* and the *processor* as two different
  groups of a same poset, *Actors* for instance, it is possible to write
  a policy in Hapi to handle that rule.

  
### Article 71
[https://gdpr.eu/article-71-data-protection-board-annual-reports/](https://gdpr.eu/article-71-data-protection-board-annual-reports/)

Paragraph 2:
"The annual report shall include a review of the practical application of the
guidelines, recommendations and best practices referred to in point (l) of
Article 70(1) as well as of the binding decisions referred to in Article 65."

- Hapi is an example of practical application that can fill the list of best
  practices.
  With the advent of data protection laws, lots of tools shall help companies
  to comply with countries rules.
  Hapi is one of these tools.

### Article 83
[https://gdpr.eu/article-83-conditions-for-imposing-administrative-fines/](https://gdpr.eu/article-83-conditions-for-imposing-administrative-fines/)

Paragraph 2:
"Administrative fines shall, depending on the circumstances of each individual
case, be imposed in addition to, or instead of, measures referred to in points
(a) to (h) and (j) of Article 58(2). When deciding whether to impose an
administrative fine and deciding on the amount of the administrative fine in
each individual case due regard shall be given to the following:"

- Letter **c**: Hapi is given as a support tool that complies with this
  requirement, then, administrative fines might be relaxed when a policy
  description language is used by the company (controller).
- Letter **g**: Since Hapi groups information as POSETS, it is relatevely easy
  to understand which data is infriged laws and permissions.

Parapraph 4:
"Infringements of the following provisions shall, in accordance with paragraph
2, be subject to administrative fines up to 10 000 000 EUR, or in the case of
an undertaking, up to 2 % of the total worldwide annual turnover of the
preceding financial year, whichever is higher:"
- Letter **a**: It's important to say that to avoid this imposed penalty tax,
  a Hapi policy shoud be written through a *denied by default* approach, as it
  is demanded by Article 25.