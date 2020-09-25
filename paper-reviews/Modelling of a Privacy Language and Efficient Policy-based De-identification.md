# Modelling of a Privacy Language and Efficient Policy-based De-identification
[Book][1]

## Chapter 4: Related work

The ***Privacy Policy Preferences*** is the category such  that is the most
related with the HAPI work. Once this category is focused on:
1. Legal Compliance
2. Human Readability: our key inspiration is LEGALEASE
3. Access Control: We want to provide an access control tool, where an system
   admin can choose which user will access some resource.

There are five exemplified languages in this category. But each language has
one or more implementation details that must be considered and differed of HAPI.

**APPEL**: Each user can define their own access policy, implementing a kind
of black list. Initially all data from an user are allowed to be accessed, so
the user must specify which data will have denied access for another user.

**XPref**: Is a sucessor to **APPEL** and as expressive as this one, but here
the policies are written in *XML*, so the user must write a *XML* query to
denote their blacklist.

**XPCAML**: Expresse the users' preferences and privacy terms about access a
service. But is lacking support for human-readability.

**S4P**: Has the same features as **XPCAML**. The advantage of this language is
such that its expressiveness, that allows applications in many domains.

**YAPPL**: Was developed for IoT systems, to comply with GDPR. Thus, the policy
writers are common people, so the human-readability and legibility are the target
of this language.



[1]: https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwjc-9Ooyd7rAhUAH7kGHbwbA0MQFjAAegQIBBAB&url=https%3A%2F%2Fopus4.kobv.de%2Fopus4-uni-passau%2Ffiles%2F767%2FGerl_Armin_ModellingOfAPrivacyLanguage.pdf&usg=AOvVaw1p-Fc4HHM2MmSVQeJygDh8