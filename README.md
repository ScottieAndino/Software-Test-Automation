# CS 320 Portfolio: Software Test Automation and Quality Assurance

Hey, I'm Scottie Andino, a computer science major at SNHU. This repository
holds the work I'm most proud of from CS 320 (Software Test Automation and
Quality Assurance), plus a short reflection on what I learned.

## What's in this repository

**Project One: Contact Service** (Java and JUnit 5)
- `Contact.java`: the contact object. The contact ID is required, can't be
  longer than 10 characters, and can't be changed after it's created. First
  name and last name are required and max out at 10 characters. The phone
  number is required and must be exactly 10 digits. The address is required
  and maxes out at 30 characters.
- `ContactService.java`: the service that adds contacts with a unique ID,
  deletes contacts by ID, and updates the first name, last name, phone, and
  address by ID.
- `ContactTest.java`: unit tests for the contact rules, with a valid case, an
  invalid case, and a boundary case (exactly at the limit and one past it) for
  each requirement.
- `ContactServiceTest.java`: unit tests for adding, deleting, and updating,
  including duplicate IDs, IDs that don't exist, and bad updates that should
  leave the old value alone.

**Project Two: Summary and Reflections Report**
- A short report on how my unit tests lined up with the customer requirements,
  how I checked the quality of the tests, and which testing techniques I used
  and which ones I skipped.

## Reflection

### How can I ensure that my code, program, or software is functional and secure?

For me it starts with not trusting my own code. Functional means every
requirement has a test that proves it, and I write those tests from the
requirements instead of from the code so they can't just confirm whatever I
already wrote. For every rule I try a good input, a bad input, and the edge,
like a 10 character first name that should work next to an 11 character one
that should be rejected. I also test that a bad update gets rejected and
leaves the old value untouched. After that, I check the tests themselves. Code
coverage shows how much of the code ran, but it doesn't prove a test would
notice a bug, so I broke my code on purpose in several ways, like changing a
limit from 10 to 11 or deleting the duplicate ID check, and made sure a test
failed every time. That caught a real weakness in my Task tests, and I fixed
it.

On security, at this level a lot of it comes down to input validation.
Rejecting null values, limiting lengths, only allowing digits in a phone
number, and not letting the ID be changed all keep bad data from getting into
the system. Negative tests, like a phone number with letters, prove those
protections work. I know this is only a starting point, because real security
work also covers things like authentication, storing data safely, and testing
for attacks, but checking every input is the habit I'd build on.

### How do I interpret user needs and incorporate them into a program?

I treat the requirements like a checklist from the customer. I break each
sentence into one specific rule, like "phone must be exactly 10 digits," and
then every rule gets code that enforces it and tests that prove it. I name the
tests after the rule, like `testPhoneCannotContainLetters`, so anyone can
trace a test back to a requirement. When the wording is fuzzy, I'd rather ask
a question than guess, because a wrong guess gets built into both the code and
the tests. Feedback from my instructor helped here too. I'd only tested values
over a limit and never exactly at it, so I added boundary tests, which is a
good reminder that the customer's idea of "up to 10 characters" includes 10.

### How do I approach designing software?

I like to keep it simple and start from the requirements. Each class does one
job: `Contact` holds and checks the data, and `ContactService` manages the
collection. The constructor reuses the same setters as the updates, so each
rule lives in one place and I don't have to fix the same thing twice. I store
contacts in a `HashMap` so lookups by ID are quick. I also try to write the
tests alongside the code instead of saving them for the end, since cutting
that corner builds up technical debt that costs more later. My plan going
forward is to keep the code small and clear, fix little problems right away,
and run tests automatically with a tool like GitHub Actions so nothing sneaks
past me.

## Tools

Java, JUnit 5, and the Codio environment provided for the course.
