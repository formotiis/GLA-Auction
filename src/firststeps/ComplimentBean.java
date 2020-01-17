/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firststeps;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author virte
 */

@Stateless (name = "Compliment")
@LocalBean
public class ComplimentBean implements Compliment {

    @Override
    public String compliment(String name) {
        return "You're the best, " + name + '!';
    }

}
