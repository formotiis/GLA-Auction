/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firststeps;

import javax.ejb.Local;

/**
 *
 * @author virte
 */
@Local
public interface Compliment {
    public String compliment(String name);
}
