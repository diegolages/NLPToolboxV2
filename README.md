# NLPToolboxV2
NonLinear Programming Toolbox Version 2.0

This a new version of the NonLinear Programming Toolbox. The original version used the JScience Library for most of the numeric computation, which offered a lot of flexibility, but lacks in performance. The new version is focused in the numeric performance, dropping the numeric flexibility, but not dropping features.

Many scientific challenges depend on solving nonlinear programming problems (NLP). Despite many tools (open-source, free or commercial) being available, usually they are designed as a "black-box", making it very hard to make parameter tunnings, test new approaches, create and validate new derived methods.

It is even harder to "connect" the solution of a NLP to another solution or data structure from another scientific problem. This lack of "connection", just like the problems listed above, could be solved using Software Engineering. 

After searching for similar tools, libraries or components for NLP, a few were found. The ones found suffer from the problems listed, are incomplete, not open source or not well structured to be readily usable by third parties.

So, the NLPToolbox (Nonlinear Programming Toolbox) attempts to address all these concerns, being at the same time a tool for solving NLP and a component that can be embedded in others softwares. It can solve all kinds of NLP, constrained or not, using Newton, Quasi-Newton and Conjugate Gradient Methods. This tool is open-source (GPL), so anyone has full access to the source code. The GPL (GNU General Public Licence) warrants that any future changes must still be open-source.

It intends to be the most flexible one, so that it can be used as a component to other softwares (commercial or not) or scientific experiments, with well-defined interfaces. Moreover, it has also a graphic interface (GUI) so that NLP problems can be solved in a confortable way.

Beyond being a component and a tool, it intends to help the learning of nonlinear optimization. By the open-source, readable source code, the most common optimization methods can be learned by seeing how they are implemented, used and can still be changed. It is possible to show the iteration sequence step by step.

The original paper of the first version is here:

http://dl.acm.org/citation.cfm?id=1537655&dl=ACM&coll=DL&CFID=833352861&CFTOKEN=11776898

Here is the abstract for the original version:

Nonlinear programming problems (NLP) solvers require some level of flexibility. This flexibility must be supported on the method choice, on the parameters specification and on the problem modelling.

Few of the tools currently available can address this level of flexibility. This paper presents an open-source, complete and easy tool, named NLPToolbox, to achieve this purpose.

Given its open-source characteristics, it offers the opportunity to study nonlinear programming in an iterative way: by showing how the methods works and allowing all kinds of specifications: methods and parameters.

Altough being a work continually in progress, it is already usable. It is currently used in teaching nonlinear programming and solving some kinds of NLP problems, like clustering and Support Vector Machine classification. Its future lies on the optimization of the tool itself, improving the precision of the numeric algorithms and integrating new methods.

