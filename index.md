---
layout: home
title: Home
nav_order: 1
nav_exclude: false
permalink: index.html
seo:
  type: Course
  name: Computer Science 142 Fall 2024
---

# Computer Science 142: Object-Oriented Programming


{: .mb-2 }
Fall 2024
{: .mb-2 .fs-6 .text-grey-dk-000 }

<button class="js-toggle-dark-mode dm-btn btn">Toggle Dark Mode</button>

<!--
[Ed](https://www.edstem.org/us/courses/52859/discussion/){: .btn .btn-ed}
[Lecture Recordings](https://bcourses.berkeley.edu/courses/1532352/external_tools/90481){: .btn .btn-bcourses}
[Gradescope](https://www.gradescope.com/courses/703847){: .btn .btn-gradescope}
[Textbook](https://inferentialthinking.com/chapters/intro.html){: .btn .btn-textbook}
[Extensions](https://docs.google.com/forms/d/e/1FAIpQLScIjB9LSxV7UPKdNrAWbPJWJMJqV05P3jyznuAtAqQPmB79EA/viewform?usp=sf_link){: .btn .btn-extensions}
[Jump to Current Week](#week-{{ site.current_week }}){: .btn .btn-currweek}
-->

<!--
## Announcements


{% assign announcements = site.announcements | reverse %}
{% for announcement in announcements %}
{{ announcement }}
{% endfor %}
-->

## Administrivia
- Instructor: Phillip Kirlin
- Office hours: Mon 1-2:30, Tue 12:30-2, Wed 2-3, Thu 2-3:30.  Also available by appointment.
- [Canvas page](https://rhodes.instructure.com/courses/7368): Use for grades, online assignment submissions, and assignment solutions.
- [Syllabus](syllabus/syllabus-142-f24.pdf) and [additional policies](syllabus/additional-policies.pdf).
- Tutoring hours:  Sunday through Thursday evenings, 5-10pm, Briggs 001 
- <font color=red><B>Final Exam Times:</b></font> You may take the final at any of the following dates/times.  You do not need to tell me in advance which one you are coming to; just show up.  **The location for all three times is the Spence Wilson Room in Briggs.**
  - Friday, December 13, 1 PM
  - Monday, December 16, 8:30 AM
  - Tuesday, December 17, 1 PM


## Resources
- Textbooks and tutorials: *Introduction to Java* by Liang (textbook), 
        *Introduction to Programming in Java* by Sedgewick and Wayne (textbook),
        [official Java tutorials](https://docs.oracle.com/javase/tutorial/), 
        [Introduction to Programming Using Java](http://math.hws.edu/javanotes/index.html) (free online textbook)
- Java in the browser: [Repl.it](http://repl.it/new/java), <a href="http://codehs.com">CodeHS</a>, <a href="http://onlinegdb.com">OnlineGDB</a>
- [Official Java documentation (Java API)](https://docs.oracle.com/en/java/javase/21/docs/api/)

{% assign mods = site.modules | where: 'class', 'Berkeley' %}
{% assign active-mods = '' | split: '' %}

{% for mod in mods %}
  {% if mod.status == 'Active' %}
    {% assign active-mods = active-mods | push: mod %}
  {% endif %}
{% endfor %}

{% for module in active-mods %}
  {{ module }}
{% endfor %}


<!--DARKMODE UNDER CONSTRUCTION-->
<br />


<!--
<p class="dm-text">The Data 8 Website Dark Mode&trade; is in beta. You can provide feedback about the website <a href="https://forms.gle/64xx2B1Y7K32bNhR9" 
class="yellow-link">here</a></p>
-->

<script src="assets/darkmode.js"></script>
<script>
  const toggleDarkMode = document.querySelector('.js-toggle-dark-mode');

  jtd.addEvent(toggleDarkMode, 'click', function(){
    if (jtd.getTheme() === 'custom_dark') {
      jtd.setTheme('light');
      localStorage.setItem("darkMode", 0);
      toggleDarkMode.innerHTML = "Toggle Dark Mode";
      toggleDarkMode.classList.add('dm-btn');
        toggleDarkMode.classList.remove('dm-dark-btn');
    } else {
      jtd.setTheme('custom_dark');
      localStorage.setItem("darkMode", 1);
      toggleDarkMode.innerHTML = "Return to the Light";
      toggleDarkMode.classList.add('dm-dark-btn');
      toggleDarkMode.classList.remove('dm-btn');
    }
  });

    window.addEventListener("DOMContentLoaded", (event) => {
      onLoad();
  });
</script>
