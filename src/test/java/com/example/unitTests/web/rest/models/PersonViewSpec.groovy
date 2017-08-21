package com.example.unitTests.web.rest.models

import spock.lang.Specification

class PersonViewSpec extends Specification {

    def "When I create a new job view with all arguments I expect a job object with the correct fields"() {
        given: "An existing jobView and familyView"
        def jobView = new JobView(company, title)
        def family1 = new FamilyView(familyName)
        def List<FamilyView> familyViewList = new ArrayList<>()
        familyViewList.add(family1)


        when: "I create a person with their personal inforamtion"
        def personView = new PersonView(firstName, lastName, jobView, familyViewList)

        then: "I expect the values to be what was set on creation"
        personView.getFirstName().equalsIgnoreCase(firstName)
        personView.getLastName().equalsIgnoreCase(lastName)
        personView.getJob() == jobView
        personView.getFamily() == familyViewList

        where:
        firstName | lastName | company       | title               | familyName
        "John"    | "Smith"  | "Capital One" | "Software Engineer" | "Smith"
        "Jane"    | "Doe"    | "Capital One" | "Product Manager"   | "Doe"

    }

    def "When I get a jobs title I expect to get the proper value back"() {}

    def "When I set a jobs title I expect the proper value to be set"() {}

    def "When I get a jobs pay I expect to get the proper value back"() {}

    def "When I set a jobs pay I expect the proper value to be set"() {}
}