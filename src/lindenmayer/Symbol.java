/*
 * Copyright 2020 Mikl&oacute;s Cs&#369;r&ouml;s.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package lindenmayer;

/**
 * Symbol in an L-system's alphabet.
 * 
 * @author Mikl&oacute;s Cs&#369;r&ouml;s
 */
public class Symbol {
    // Attributs
    private char sym;

    // Constructeur
    Symbol(char sym) {
        this.sym = sym;
    }

    // Méthode de comparaison
    public boolean equals(Object other) {
        if (other == this)
            return true; // Vrai si l'objet est lui-même
        if (other == null)
            return false; // Faux si ce n'est pas un objet
        if (getClass() != other.getClass())
            return false; // Si ce n'est pas la même classe
        Symbol symbol = (Symbol) other;
        return this.sym == symbol.getChar(); // Les objets sont égaux si même caractère

    }

    // Méthode de get
    public char getChar() {
        return this.sym;
    }

}